package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import com.fasterxml.jackson.annotation.JsonView;

public class Orders {
	
	@JsonView(Views.Public.class)
	private String distributor_code;

	@ElementCollection
	@JsonView(Views.Public.class)
	private List<Order> orders;

	public Orders(List<Order> ordenLista) {
		this.orders = ordenLista;
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	

	
}
