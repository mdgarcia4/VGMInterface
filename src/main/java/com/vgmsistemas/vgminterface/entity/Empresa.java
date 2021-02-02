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

/**
 * @author Mauricio
 *
 */

@Entity
@Table( name = "empresas" )
public class Empresa implements Serializable{

	
	private static final long serialVersionUID = 3593807007462447749L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_empresa")	
	private long id;
	
	@Column(name = "sn_activada")
	private String snActivada;
	
	@Column(name = "ti_implementacion_interfaz")
	private String tiImplementacionInterfaz;
	
	
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	public String getTiImplementacionInterfaz() {
		return tiImplementacionInterfaz;
	}
	public void setTiImplementacionInterfaz(String tiImplementacionInterfaz) {
		this.tiImplementacionInterfaz = tiImplementacionInterfaz;
	}
	public String getSnActivada() {
		return snActivada;
	}
	public void setSnActivada(String snActivada) {
		this.snActivada = snActivada;
	}

	
		
}
