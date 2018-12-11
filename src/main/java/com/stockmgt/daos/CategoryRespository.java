package com.stockmgt.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmgt.entities.Category;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Integer> {

}
