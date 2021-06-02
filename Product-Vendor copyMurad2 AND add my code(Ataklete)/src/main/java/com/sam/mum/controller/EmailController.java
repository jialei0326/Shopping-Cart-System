package com.sam.mum.controller;


import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.mum.model.MailConfig;
import com.sam.mum.model.Order;
import com.sam.mum.model.Email;

@Controller

public class EmailController {
	
	private MailConfig sender;
	private Email email;
	@Value("${mail.from}")
	private String names;
	

public EmailController(MailConfig sender) {

    this.sender = sender;
}



@PostMapping("/email")
public String  sendMail(
		             @ModelAttribute("name") String name,
		             @ModelAttribute("email") String email,
		             @ModelAttribute("feedback") String feedback,
		             BindingResult bindingResult
		             )  {
    
	   System.out.println(email);
	   
	if(bindingResult.hasErrors()){
        throw  new ValidationException("Mail is not valid");
    }
       Email e = new Email();
       e.setName(name);
       e.setEmail(email);
       e.setFeedback(feedback);

    JavaMailSenderImpl helper = new JavaMailSenderImpl();
    helper.setHost(sender.getHost());
    helper.setPort(sender.getPort());
    helper.setUsername(sender.getUsername());
    helper.setPassword(sender.getPassword());
    

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(names);
    mailMessage.setTo(email);
    mailMessage.setSubject("New email from " + name);
    mailMessage.setText(feedback);
    helper.send(mailMessage);
    
  return "confirm";
}

}
