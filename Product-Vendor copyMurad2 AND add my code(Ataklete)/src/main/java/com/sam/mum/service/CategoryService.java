package com.sam.mum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sam.mum.model.Category;
import com.sam.mum.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	public void addCategory(Category category) {
		
		categoryRepository.save(category);
	}
	
	public List<Category> getAllCategory(){
		
		return categoryRepository.findAll();
	}
	
	public void deleteCategoryById(int id) {
		
		categoryRepository.deleteById(id);	
	}
	
	public Optional<Category> findCategoryById(int id){
		
		return categoryRepository.findById(id);
	}
}
