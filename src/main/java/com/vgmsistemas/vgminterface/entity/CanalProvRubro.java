/**
 * 
 */
package com.vgmsistemas.vgminterface.entity;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table( name = "canal_prov_rubro" )
public class CanalProvRubro implements Serializable{

	private static final long serialVersionUID = -3526222031109421299L;

	@EmbeddedId
	private PkCanalProvRubro id;

	public PkCanalProvRubro getId() {
		return id;
	}

	public void setId(PkCanalProvRubro id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CanalProvRubro other = (CanalProvRubro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CanalProvRubro [id=" + id + "]";
	}

	public CanalProvRubro(PkCanalProvRubro id) {
		super();
		this.id = id;
	}

	
	public CanalProvRubro() {
		
	}

}
