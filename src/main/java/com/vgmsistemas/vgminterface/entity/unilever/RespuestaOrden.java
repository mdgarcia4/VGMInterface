
package com.vgmsistemas.vgminterface.entity.unilever;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "respuestas_interface")
public class RespuestaOrden implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270129122930333566L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "fe_respuesta")
	private Date feRespuesta;
	
	@Column(name="de_tabla")
	private String Tabla;
	
	@OneToMany(mappedBy = "respuesta", cascade=CascadeType.PERSIST)
	private List<RespuestaOrdenDetalle> orders;
	
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFeRespuesta() {
		return feRespuesta;
	}

	public void setFeRespuesta(Date feRespuesta) {
		this.feRespuesta = feRespuesta;
	}

	public String getTabla() {
		return Tabla;
	}

	public void setTabla(String tabla) {
		Tabla = tabla;
	}

	public List<RespuestaOrdenDetalle> getOrders() {
		return orders;
	}

	public void setOrders(List<RespuestaOrdenDetalle> orders) {
		this.orders = orders;
	}

		
				
}
