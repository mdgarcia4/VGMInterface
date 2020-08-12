package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.List;

import javax.persistence.ElementCollection;

import com.fasterxml.jackson.annotation.JsonView;

public class Inventories {
	
	@JsonView(Views.Public.class)
	private String distributor_code;

	@ElementCollection
	@JsonView(Views.Inventory.class)
	private List<Inventory> sourceItems;

	public Inventories(List<Inventory> sourceItems) {
		this.sourceItems = sourceItems;
	}

	public List<Inventory> getSourceItems() {
		return sourceItems;
	}

	public void setSourceItems(List<Inventory> sourceItems) {
		this.sourceItems = sourceItems;
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}
	
}
