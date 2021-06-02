package com.sam.mum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.mum.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
