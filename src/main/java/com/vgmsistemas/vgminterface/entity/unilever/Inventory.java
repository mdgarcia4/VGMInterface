
package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "EAN","stockitem_qty","stockitem_is_in_stock","min_thersold_qty"})
@Table( name = "v_stock_interface")
public class Inventory{
	@Id
	private PkInventory id;
	
	@JsonView(Views.Inventory.class)
	@Column(name="EAN")
	private String EAN;
		
	@JsonView(Views.Inventory.class)
	@Column(name = "stockitem_qty")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer stockitem_qty;
	
	@JsonView(Views.Inventory.class)
	@Column(name = "stockitem_is_in_stock")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer stockitem_is_in_stock;
	
	@JsonView(Views.Inventory.class)
	@Column(name = "min_thersold_qty")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer min_thersold_qty;
	
	
	public PkInventory getId() {
		return id;
	}


	public void setId(PkInventory id) {
		this.id = id;
	}


	public String getEAN() {
		return EAN.trim();
	}


	public void setEAN(String eAN) {
		EAN = eAN;
	}


	public Integer getStockitem_qty() {
		return stockitem_qty;
	}


	public void setStockitem_qty(Integer stockitem_qty) {
		this.stockitem_qty = stockitem_qty;
	}


	public Integer getStockitem_is_in_stock() {
		return stockitem_is_in_stock;
	}


	public void setStockitem_is_in_stock(Integer stockitem_is_in_stock) {
		this.stockitem_is_in_stock = stockitem_is_in_stock;
	}


	public Integer getMin_thersold_qty() {
		return min_thersold_qty;
	}


	public void setMin_thersold_qty(Integer min_thersold_qty) {
		this.min_thersold_qty = min_thersold_qty;
	}
		
}
