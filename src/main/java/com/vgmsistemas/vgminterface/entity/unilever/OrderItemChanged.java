package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden_detalle_editados" )
@JsonPropertyOrder({ "EAN","reason"})
public class OrderItemChanged{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_orden_detalle")
	private Integer idOrdenDetalle;
	
	@JsonView(Views.Orden.class)
	@Column(name = "id_codigobarras")
	private String EAN;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_razon")
	private String reason;
	
	@ManyToOne
    @JoinColumn(name = "id_orden")
    private Order order3;
	
	@Column(name = "ti_estado")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdOrdenDetalle() {
		return idOrdenDetalle;
	}

	public void setIdOrdenDetalle(Integer idOrdenDetalle) {
		this.idOrdenDetalle = idOrdenDetalle;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Order getOrder3() {
		return order3;
	}

	public void setOrder3(Order order3) {
		this.order3 = order3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
		
}
