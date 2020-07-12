
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
public class RespuestaDetalle implements Serializable{
	
	private static final long serialVersionUID = 2062025126078238729L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "ean" )
	private String EAN;
	
	@Column(name = "de_estado")
	private String status_prod;
	
	@Column(name = "de_estado_precio")
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "id_respuesta")
    private Respuesta respuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getStatus_prod() {
		return status_prod;
	}

	public void setStatus_prod(String status_prod) {
		this.status_prod = status_prod;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
