package com.poly.dao;


import com.poly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p where p.category.id=?1")
    List<Product> findByCategoryId(String cid);
}

