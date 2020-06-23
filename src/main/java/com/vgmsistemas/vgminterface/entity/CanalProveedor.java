/**
 * 
 */
package com.vgmsistemas.vgminterface.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table( name = "canal_proveedor" )
public class CanalProveedor implements Serializable{

	private static final long serialVersionUID = -3526222031109421299L;

	@EmbeddedId
	private PkCanalProveedor id;

	@Column(name = "de_canal_prov")
	private String deCanalProv;

	public PkCanalProveedor getId() {
		return id;
	}

	public void setId(PkCanalProveedor id) {
		this.id = id;
	}

	public String getDeCanalProv() {
		return deCanalProv;
	}

	public void setDeCanalProv(String deCanalProv) {
		this.deCanalProv = deCanalProv;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deCanalProv == null) ? 0 : deCanalProv.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CanalProveedor other = (CanalProveedor) obj;
		if (deCanalProv == null) {
			if (other.deCanalProv != null)
				return false;
		} else if (!deCanalProv.equals(other.deCanalProv))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public CanalProveedor(PkCanalProveedor id, String deCanalProv) {
		super();
		this.id = id;
		this.deCanalProv = deCanalProv;
	}

	

}
