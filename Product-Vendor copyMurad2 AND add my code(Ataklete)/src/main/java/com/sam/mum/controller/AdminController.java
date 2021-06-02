package com.sam.mum.controller;


import java.util.Optional;


import com.sam.mum.model.PreApprovalProducts;

import com.sam.mum.repository.UserRepository;
import com.sam.mum.service.PreApprovalProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.sam.mum.model.Category;
import com.sam.mum.model.Product;
import com.sam.mum.service.CategoryService;
import com.sam.mum.service.ProductService;


@Controller
public class AdminController {

	public static String UrlDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	 
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	ProductService productservice;

	@Autowired
	PreApprovalProductsService preApprovalProductsService;

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}

	@GetMapping("/admin/all-users")
	public String getAllVendors(Model model){

		model.addAttribute("users", userRepository.findAll());

		return "allUsers";

	}

	@GetMapping("/admin/users/delete")
	public String deleteVendor(@RequestParam("id") Integer id){

		userRepository.deleteById(id);

		return "redirect:/admin/all-users";
	}



	@GetMapping("/admin/products/approval-page")
	public String getApprovalPage(Model model){

		model.addAttribute("approvals", preApprovalProductsService.getAllProducts());

		return "productsApproval";
	}

	@GetMapping("/admin/products/approve")
	public String approveProduct(@RequestParam("id") Long id){

		PreApprovalProducts preApprovalProducts = preApprovalProductsService.getProductById(id).get();
		preApprovalProducts.setApproved(true);

		Product product = new Product();

		product.setName(preApprovalProducts.getName());
		product.setPrice(preApprovalProducts.getPrice());
		product.setWeight(preApprovalProducts.getWeight());
		product.setDescription(preApprovalProducts.getDescription());
		product.setCategory(preApprovalProducts.getCategory());
		product.setImageName(preApprovalProducts.getImageName());

		productservice.addProduct(product);
		preApprovalProductsService.removeApprovedProductsFromApprovalPage();

		return "redirect:/admin/products/approval-page";
	}

	@GetMapping("/admin/products/decline")
	public String declineProduct(@RequestParam("id") Long id){

		preApprovalProductsService.deleteProductById(id);
		preApprovalProductsService.removeApprovedProductsFromApprovalPage();

		return "redirect:/admin/products/approval-page";

	}

	@GetMapping("/admin/vendor/registration")
	public String getVendorRegistrationPage(Model model){

		return "vendorRegistration";

	}

	@GetMapping("/admin/categories")
	public String getAddCat(Model model) {

		model.addAttribute("categories", categoryservice.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {

		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete")
	public String deleteCat(@RequestParam("id") int id) {

		categoryservice.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update")
	public String updateCat(@RequestParam("id") int id, Model model) {

		Optional<Category> category =  categoryservice.findCategoryById(id);
		if(category.isPresent()) {

			model.addAttribute("category", category);

			return "categoriesAdd";
		}else {

			return "404";
		}
	}
	

}
