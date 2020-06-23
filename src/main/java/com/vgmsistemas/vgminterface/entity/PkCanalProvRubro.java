/**
 * 
 */
package com.vgmsistemas.vgminterface.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkCanalProvRubro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "id_proveedor")
	private long idProveedor;

	@Column(name = "id_canal_prov")
	private long idCanalProv;
	
	@Column(name="id_rubro")
	private long idRubro;

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

	public long getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(long idRubro) {
		this.idRubro = idRubro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCanalProv ^ (idCanalProv >>> 32));
		result = prime * result + (int) (idProveedor ^ (idProveedor >>> 32));
		result = prime * result + (int) (idRubro ^ (idRubro >>> 32));
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
		PkCanalProvRubro other = (PkCanalProvRubro) obj;
		if (idCanalProv != other.idCanalProv)
			return false;
		if (idProveedor != other.idProveedor)
			return false;
		if (idRubro != other.idRubro)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PkCanalProvRubro [idProveedor=" + idProveedor + ", idCanalProv=" + idCanalProv + ", idRubro=" + idRubro
				+ "]";
	}

	public PkCanalProvRubro(long idProveedor, long idCanalProv, long idRubro) {
		super();
		this.idProveedor = idProveedor;
		this.idCanalProv = idCanalProv;
		this.idRubro = idRubro;
	}

	public PkCanalProvRubro() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}