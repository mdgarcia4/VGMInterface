package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden_envio" )
public class OrderShipping{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
			
	@OneToOne
    @JoinColumn(name = "id_orden")
    private Order order2;
	
	@JsonView(Views.Orden.class)
	@OneToOne(mappedBy = "shipping", cascade=CascadeType.PERSIST)
	private OrderShippingAddress address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder2() {
		return order2;
	}

	public void setOrder2(Order order2) {
		this.order2 = order2;
	}

	public OrderShippingAddress getAddress() {
		return address;
	}

	public void setAddress(OrderShippingAddress address) {
		this.address = address;
	}

	
	
}
