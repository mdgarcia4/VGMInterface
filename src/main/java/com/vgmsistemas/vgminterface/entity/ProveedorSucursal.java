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
@Table( name = "proveedor_sucursal" )
public class ProveedorSucursal implements Serializable{

	
	private static final long serialVersionUID = 3593807007462447749L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_proveedor_sucursal")	
	private long id;
	
	@Column(name = "id_sucursal")	
	private long idSucursal;
	
	@Column(name = "id_proveedor")	
	private long idProveedor;
	
	@Column(name = "id_cliente_prov")
	private String idClienteProv;
	
	
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	public long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getIdClienteProv() {
		return idClienteProv;
	}
	public void setIdClienteProv(String idClienteProv) {
		this.idClienteProv = idClienteProv;
	}
	

	
		
}
