package com.sam.mum.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Primary;



@Entity 
@Table(name = "shoppingOrder")
@Primary
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String shippingAddress;
    private double subtotal;
    private String phoneNumber;
    private String email;
    
    
	public Order() {
	
	}
	
	public Order(Integer orderId, Integer customerId, String firstName, String lastName, String shippingAddress,
			double subtotal, String phoneNumber, String email) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.shippingAddress = shippingAddress;
		this.subtotal = subtotal;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}   