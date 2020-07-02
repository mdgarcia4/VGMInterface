package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.List;

import javax.persistence.ElementCollection;

public class Products {
	
	
	private String distributor_code;

	@ElementCollection
	private List<Product> products;

	public Products(List<Product> products) {
		this.products= products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}
	
	
	
}
