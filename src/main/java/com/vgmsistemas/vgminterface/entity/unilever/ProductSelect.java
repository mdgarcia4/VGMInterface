
package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table( name = "etiquetas")
public class ProductSelect{

	@Id
	@Column(name = "id")	
	private Long id;
	
	@Column(name="id_codigobarras")
	@JsonView(Views.Public.class)
	private String EAN;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEAN() {
		return EAN.trim();
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}
	
		
}
