package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.List;

import javax.persistence.ElementCollection;

import com.fasterxml.jackson.annotation.JsonView;

public class Prices {
	
	@JsonView(Views.Public.class)
	private String distributor_code;

	@ElementCollection
	@JsonView(Views.Precio.class)
	private List<Product> products;

	public Prices(List<Product> products) {
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
