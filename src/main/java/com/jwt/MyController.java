package com.jwt;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
public class MyController {
    @Autowired
    public MyService repo;

    @PostMapping("/product/")
   
    public ResponseEntity<Object> addProduct(@RequestBody Product p) {
        try {
            Product savedProduct = repo.save(p);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product: " + e.getMessage());
        }
    }

    @GetMapping("/product/")
    public ResponseEntity<Object> getAllProduct() {
        try {
            List<Product> products = repo.findAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving products: " + e.getMessage());
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") int id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product: " + e.getMessage());
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value = "id") int id) {
        try {
            Optional<Product> product = Optional.ofNullable(repo.findById(id));
            if (product.isPresent()) {
                return ResponseEntity.ok(product.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving product: " + e.getMessage());
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product p, @PathVariable int id) {
        try {
            Optional<Product> existingProduct = Optional.ofNullable(repo.findById(id));
            if (!existingProduct.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            p.setId(id);
            repo.save(p);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product: " + e.getMessage());
        }
    }

    @GetMapping("/productp/{price}")
    public ResponseEntity<Object> findByPrice(@PathVariable("price") int price) {
        try {
            List<Product> products = repo.findByPrice(price);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
        }
    }

    @GetMapping("/productn/{pname}")
    public ResponseEntity<Object> findByPname(@PathVariable("pname") String pname) {
        try {
            List<Product> products = repo.findByProductName(pname);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by name: " + e.getMessage());
        }
    }

    @GetMapping("/products/{company}")
    public ResponseEntity<Object> findByCName(@PathVariable("company") String company) {
        try {
            List<Product> products = repo.findByCompanyName(company);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by company: " + e.getMessage());
        }
    }
}
