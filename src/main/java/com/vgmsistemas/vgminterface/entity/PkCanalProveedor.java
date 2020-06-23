/**
 * 
 */
package com.vgmsistemas.vgminterface.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkCanalProveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "id_proveedor")
	private long idProveedor;

	@Column(name = "id_canal_prov")
	private long idCanalProv;

	public PkCanalProveedor() {

	}

	public PkCanalProveedor(long idProveedor, long idCanalProv) {
		super();
		this.idProveedor = idProveedor;
		this.idCanalProv = idCanalProv;
	}

	public long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public long getIdCanalProv() {
		return idCanalProv;
	}

	public void setIdCanalProv(long idCanalProv) {
		this.idCanalProv = idCanalProv;
	}

	@Override
	public String toString() {
		return "PkCanalProveedor [idProveedor=" + idProveedor + ", idCanalProv=" + idCanalProv + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCanalProv ^ (idCanalProv >>> 32));
		result = prime * result + (int) (idProveedor ^ (idProveedor >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PkCanalProveedor other = (PkCanalProveedor) obj;
		if (idCanalProv != other.idCanalProv)
			return false;
		if (idProveedor != other.idProveedor)
			return false;
		return true;
	}

}