package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden_pago" )
@JsonPropertyOrder({ "amount_ordered","method"})
public class OrderPayment{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(Views.Orden.class)
	@Column(name = "ti_metodo_pago")
	private String method;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_pago")
	private Float amount_ordered;
		
	@OneToOne
    @JoinColumn(name = "id_orden")
    private Order order1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Float getAmount_ordered() {
		return amount_ordered;
	}

	public void setAmount_ordered(Float amount_ordered) {
		this.amount_ordered = amount_ordered;
	}

	public Order getOrder1() {
		return order1;
	}

	public void setOrder1(Order order1) {
		this.order1 = order1;
	}

	

			
}
