package com.sam.mum.controller;


import com.sam.mum.global.GlobalData;
import com.sam.mum.model.Order;
import com.sam.mum.model.Product;
import com.sam.mum.model.User;

import com.sam.mum.repository.OrderRepository;
import com.sam.mum.repository.UserRepository;
import com.sam.mum.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userrepository;
   
   
    @PostMapping("/payNow")
//    @ResponseBody
    public String payNow(@ModelAttribute("order") Order order ,Model model){
    	    	
//    	String name = order.getFirstName();
//    	String email = order.getEmail();
//    	String shipping = order.getShippingAddress();
//    	
//    	Email o = new Email();
//    	o.(email);
//    	 a.getOrder(order);
    	     	
        order.setSubtotal(GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        Optional<User> user = userrepository.findUserByEmail(order.getEmail());
        
        model.addAttribute("name", order.getFirstName());
        model.addAttribute("email", order.getEmail());
        model.addAttribute("feedback", order.getShippingAddress());

        if(user.isPresent()){
            order.setCustomerId(user.get().getId());
            orderService.creatOrder(order);

            // clear the shopping cart
            GlobalData.cart.clear();
        }else{
            // user not exit
        }
    
        return "orderPlaced";
    }
    


}