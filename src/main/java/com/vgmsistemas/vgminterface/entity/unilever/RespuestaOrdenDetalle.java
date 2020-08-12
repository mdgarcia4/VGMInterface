
package com.vgmsistemas.vgminterface.entity.unilever;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "respuestas_interface_detalle")
public class RespuestaOrdenDetalle implements Serializable{
	
	private static final long serialVersionUID = 2062025126078238729L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
		
	@Column(name = "id_orden")
	private String order_id;
	
	@Column(name = "de_estado_orden")
	private String status;
	
	
	@ManyToOne
    @JoinColumn(name = "id_respuesta")
    private RespuestaOrden respuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RespuestaOrden getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaOrden respuesta) {
		this.respuesta = respuesta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
