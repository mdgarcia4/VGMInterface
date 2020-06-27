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
@Table( name = "parametros_interface" )
public class ParametroInterface implements Serializable{

	private static final long serialVersionUID = -6096220149840555434L;
	@Id
	@GeneratedValue
	@Column(name = "id_empresa")
	private long id;
	
	@Column(name = "de_url_cuentacliente_estado")
	private String deUrlCuentaClienteEstado;
	
	@Column(name = "de_url_catalogo")
	private String deUrlCatalogo;
	
	@Column(name = "de_url_stock")
	private String deUrlStock;
	
	@Column(name = "de_url_precios")
	private String deUrlPrecio;
	
	@Column(name = "de_url_estado_ventas")
	private String deUrlEstadoVentas;
	
	@Column(name = "client_id")
	private String client_id;
	
	@Column(name = "client_secret")
	private String client_secret;
	
	@Column(name = "id_proveedor")
	private long idProveedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeUrlCuentaClienteEstado() {
		return deUrlCuentaClienteEstado;
	}

	public void setDeUrlCuentaClienteEstado(String deUrlCuentaClienteEstado) {
		this.deUrlCuentaClienteEstado = deUrlCuentaClienteEstado;
	}

	public String getDeUrlCatalogo() {
		return deUrlCatalogo;
	}

	public void setDeUrlCatalogo(String deUrlCatalogo) {
		this.deUrlCatalogo = deUrlCatalogo;
	}

	public String getDeUrlStock() {
		return deUrlStock;
	}

	public void setDeUrlStock(String deUrlStock) {
		this.deUrlStock = deUrlStock;
	}

	public String getDeUrlPrecio() {
		return deUrlPrecio;
	}

	public void setDeUrlPrecio(String deUrlPrecio) {
		this.deUrlPrecio = deUrlPrecio;
	}

	public String getDeUrlEstadoVentas() {
		return deUrlEstadoVentas;
	}

	public void setDeUrlEstadoVentas(String deUrlEstadoVentas) {
		this.deUrlEstadoVentas = deUrlEstadoVentas;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client_id == null) ? 0 : client_id.hashCode());
		result = prime * result + ((client_secret == null) ? 0 : client_secret.hashCode());
		result = prime * result + ((deUrlCatalogo == null) ? 0 : deUrlCatalogo.hashCode());
		result = prime * result + ((deUrlCuentaClienteEstado == null) ? 0 : deUrlCuentaClienteEstado.hashCode());
		result = prime * result + ((deUrlEstadoVentas == null) ? 0 : deUrlEstadoVentas.hashCode());
		result = prime * result + ((deUrlPrecio == null) ? 0 : deUrlPrecio.hashCode());
		result = prime * result + ((deUrlStock == null) ? 0 : deUrlStock.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ParametroInterface other = (ParametroInterface) obj;
		if (client_id == null) {
			if (other.client_id != null)
				return false;
		} else if (!client_id.equals(other.client_id))
			return false;
		if (client_secret == null) {
			if (other.client_secret != null)
				return false;
		} else if (!client_secret.equals(other.client_secret))
			return false;
		if (deUrlCatalogo == null) {
			if (other.deUrlCatalogo != null)
				return false;
		} else if (!deUrlCatalogo.equals(other.deUrlCatalogo))
			return false;
		if (deUrlCuentaClienteEstado == null) {
			if (other.deUrlCuentaClienteEstado != null)
				return false;
		} else if (!deUrlCuentaClienteEstado.equals(other.deUrlCuentaClienteEstado))
			return false;
		if (deUrlEstadoVentas == null) {
			if (other.deUrlEstadoVentas != null)
				return false;
		} else if (!deUrlEstadoVentas.equals(other.deUrlEstadoVentas))
			return false;
		if (deUrlPrecio == null) {
			if (other.deUrlPrecio != null)
				return false;
		} else if (!deUrlPrecio.equals(other.deUrlPrecio))
			return false;
		if (deUrlStock == null) {
			if (other.deUrlStock != null)
				return false;
		} else if (!deUrlStock.equals(other.deUrlStock))
			return false;
		if (id != other.id)
			return false;
		if (idProveedor != other.idProveedor)
			return false;
		return true;
	}
	
	
	
}
