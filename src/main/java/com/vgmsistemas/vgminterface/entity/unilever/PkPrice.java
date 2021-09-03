/**
 * 
 */
package com.vgmsistemas.vgminterface.entity.unilever;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkPrice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_articulos")	
	private Long idArticulos;
	
	@Column(name="ti_lista_interface ")
	private String tiListaInterface ;
	
	@Column(name="id_sucursal")
	private Long idSucursal;
	
	@Column(name="id_cliente")
	private Long idCliente;
	
	@Column(name="id_comercio")
	private Long idComercio;

	@Column(name="id_canal")
	private String id_canal;
	
	public String getId_canal() {
		return id_canal;
	}

	public void setId_canal(String id_canal) {
		this.id_canal = id_canal;
	}

	public Long getIdArticulos() {
		return idArticulos;
	}

	public void setIdArticulos(Long idArticulos) {
		this.idArticulos = idArticulos;
	}

	public String getTiListaInterface() {
		return tiListaInterface;
	}

	public void setTiListaInterface(String tiListaInterface) {
		this.tiListaInterface = tiListaInterface;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdComercio() {
		return idComercio;
	}

	public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}
	
		
}
