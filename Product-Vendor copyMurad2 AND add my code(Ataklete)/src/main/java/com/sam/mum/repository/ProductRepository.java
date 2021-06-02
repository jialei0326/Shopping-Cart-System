package com.sam.mum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sam.mum.model.Product;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategory_Id(int id);

	@Query("select p from Product p where p.name like %:keyword%")
	List<Product> findAllByNameLike(String keyword);

}
