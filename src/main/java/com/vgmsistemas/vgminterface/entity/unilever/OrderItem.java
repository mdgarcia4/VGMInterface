package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden_detalle" )
@JsonPropertyOrder({ "name","industry","original_price","price","price_incl_tax",
					 "qty_ordered","discount_amount","row_total","row_total_inclusive_tax",
					 "EAN","row_weight"})
public class OrderItem{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_articulo")
	private String name;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_proveedor")
	private String industry;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_unitario_original")
	private Float original_price;
		
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_unitario_civa")
	private Float price;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_unitario_cimp")
	private Float price_incl_tax;
	
	@JsonView(Views.Orden.class)
	@Column(name = "id_codigobarras")
	private String EAN;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "id_articulo_interface")
	private Integer product_id;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "ca_articulos")
	private Float qty_ordered;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_dto_item")
	private Float discount_amount;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_total_item_civa")
	private Float row_total;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "pr_total_item_cimp")
	private Float row_total_inclusive_tax;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	@Column(name = "ca_peso_total")
	private Float row_weight;
	
	@ManyToOne
    @JoinColumn(name = "id_orden")
    private Order order;
	
	@Column(name = "ca_articulos_fc")
	private Float caArticulosFc;
	
	@Column(name = "ca_articulos_nc")
	private Float caArticulosNc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Float getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(Float original_price) {
		this.original_price = original_price;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPrice_incl_tax() {
		return price_incl_tax;
	}

	public void setPrice_incl_tax(Float price_incl_tax) {
		this.price_incl_tax = price_incl_tax;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Float getQty_ordered() {
		return qty_ordered;
	}

	public void setQty_ordered(Float qty_ordered) {
		this.qty_ordered = qty_ordered;
	}

	public Float getRow_total() {
		return row_total;
	}

	public void setRow_total(Float row_total) {
		this.row_total = row_total;
	}

	public Float getRow_total_inclusive_tax() {
		return row_total_inclusive_tax;
	}

	public void setRow_total_inclusive_tax(Float row_total_inclusive_tax) {
		this.row_total_inclusive_tax = row_total_inclusive_tax;
	}

	public Float getRow_weight() {
		return row_weight;
	}

	public void setRow_weight(Float row_weight) {
		this.row_weight = row_weight;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Float getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(Float discount_amount) {
		this.discount_amount = discount_amount;
	}

	public Float getCaArticulosFc() {
		return caArticulosFc;
	}

	public void setCaArticulosFc(Float caArticulosFc) {
		this.caArticulosFc = caArticulosFc;
	}

	public Float getCaArticulosNc() {
		return caArticulosNc;
	}

	public void setCaArticulosNc(Float caArticulosNc) {
		this.caArticulosNc = caArticulosNc;
	}
	
	
	
		
}
