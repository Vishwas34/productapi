package com.jwt;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MyService extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    void deleteById(int id);

    Product findById(int id);

    @Query("from Product where price > ?1")
    List<Product> findByPrice(int price);

    @Query("from Product where pname LIKE %?1%")
    List<Product> findByProductName(String pname);

    @Query("from Product where company LIKE %?1%")
    List<Product> findByCompanyName(String company);
}
