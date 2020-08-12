/**
 * 
 */
package com.vgmsistemas.vgminterface.entity.unilever;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PkInventory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="id_deposito")
	private Long idDeposito;
	
	@Column(name = "id_articulos")	
	private Long idArticulos;

	public Long getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(Long idDeposito) {
		this.idDeposito = idDeposito;
	}

	public Long getIdArticulos() {
		return idArticulos;
	}

	public void setIdArticulos(Long idArticulos) {
		this.idArticulos = idArticulos;
	}
	
			
}
