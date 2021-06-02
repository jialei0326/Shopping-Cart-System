package com.sam.mum.global;

import java.util.ArrayList;
import java.util.List;

import com.sam.mum.model.Product;

public class GlobalData {

	// TODO: should be map? one user one cart.
	public static List<Product> cart;

	
	static {
		
		cart = new ArrayList<>();
	}
}
