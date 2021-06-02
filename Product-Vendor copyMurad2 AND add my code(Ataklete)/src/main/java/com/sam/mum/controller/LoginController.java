package com.sam.mum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sam.mum.global.GlobalData;
import com.sam.mum.model.Role;
import com.sam.mum.model.User;
import com.sam.mum.repository.RoleRepository;
import com.sam.mum.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userrepository;
	@Autowired
	RoleRepository roleRepository;


	@GetMapping("/login")
	public String login() {
		
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		return "redirect:/login";
	}

//	@GetMapping("/modal")
//	public String modalGet() {
//		return "modal1";

	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest) throws ServletException {

		String password;

		if(user.getPassword().equals(user.getConfirmedPassword())){
			password = user.getPassword();
		}else{
			return "redirect:/register";
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(password));
		
		List<Role> roles = new ArrayList<>();
		
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userrepository.save(user);
		httpServletRequest.login(user.getEmail(), user.getPassword());
	
		return "redirect:/login";
		

		
	}

	@PostMapping("/vendor/registration")
	public String authenticate(@ModelAttribute("user") User user, HttpServletRequest httpServletRequest) throws ServletException {

		String password;

		if(user.getPassword().equals(user.getConfirmedPassword())){
			password = user.getPassword();
		}else{
			return "redirect:/register";
		}

		user.setPassword(bCryptPasswordEncoder.encode(password));

		List<Role> roles = new ArrayList<>();

		roles.add(roleRepository.findById(3).get());
		user.setRoles(roles);
		userrepository.save(user);


		return "redirect:/admin";
	}

}

