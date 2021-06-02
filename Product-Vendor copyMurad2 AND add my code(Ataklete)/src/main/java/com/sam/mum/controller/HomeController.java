package com.sam.mum.controller;

import com.sam.mum.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sam.mum.global.GlobalData;
import com.sam.mum.service.CategoryService;
import com.sam.mum.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
    ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size() );
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		return "shop";
	}


	

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id, Model model) {
		
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String productDetails(@PathVariable int id, Model model) {
		
		model.addAttribute("cartCount", GlobalData.cart.size() );
		model.addAttribute("product", productService.getProductById(id).get());
		return "viewProduct";
	}

	@GetMapping("/search")
	public String searchProduct(@RequestParam("keyword") String keyword, Model model){
		List<Product> tmp = productService.getByKeyword(keyword);
		if(tmp != null && !tmp.isEmpty()){
			model.addAttribute("searchResult", tmp);
			model.addAttribute("resultNum", tmp.size());
			model.addAttribute("keyword",keyword);
			return "search";
		}else{
			model.addAttribute("error_search_fail", "No relevant product");
			return "shop";
		}
	}
}
