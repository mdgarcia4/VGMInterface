/**
 * 
 */
package com.vgmsistemas.vgminterface.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "rubros" )
public class RubroCliente implements Serializable{

	private static final long serialVersionUID = -3526222031109421299L;

	@Id
	@GeneratedValue
	@Column(name = "id_rubro")	
	private int id;

	@Column(name = "de_rubro")
	private String descripcion;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
