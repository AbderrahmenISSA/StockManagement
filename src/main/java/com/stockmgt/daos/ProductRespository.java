package com.stockmgt.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmgt.entities.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryName(String categoryName);

}
