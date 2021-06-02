package com.sam.mum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.mum.model.Product;
import com.sam.mum.repository.ProductRepository;

@Service
public class ProductService {

	
	@Autowired
	ProductRepository productRepository;
	
	public void addProduct(Product product) {
		
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		
		return productRepository.findAll();
	}
	
	public void deleteProductById(Long id) {
		
		productRepository.deleteById(id);
		
	}
	public Optional<Product> getProductById(long id){
		
		return productRepository.findById(id);
	}
	

	public List<Product> getAllProductsByCategoryId(int id) {
		
		return productRepository.findAllByCategory_Id(id);
	}

	public List<Product> getByKeyword(String keyword){
		return productRepository.findAllByNameLike(keyword);
	}

}
