
package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table( name = "v_precios")
public class Price{

	@Id
	@Column(name = "id_articulos")	
	private Long id;
	
	@Column(name="id_codigobarras")
	private String EAN;
	
	@Column(name = "de_extra" )
	private String name_prod;
	
	@Column(name = "sn_activo")
	private String snActivo;
	
	@Transient
	private String status_prod;
	
	@Column(name="ca_unidxean")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer qty_ean_unit;
	
	@Column(name="ca_unid_min_venta")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer qty_min_sell;
	
	@Transient
	private String industry_name;
	
	@Column(name = "id_art_proveedor")
	private String industry_code;
	
	@Column(name = "de_articulos" )
	private String short_description;
	
	@Column(name = "pr_arcor_a")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Float default_price;
	
	@Transient
	private String brand;
	
	@Column(name = "id_linea")
	private Long idMarca;
	
	@Column(name = "id_proveedor")
	private Long idProveedor;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getName_prod() {
		return name_prod;
	}

	public void setName_prod(String name_prod) {
		this.name_prod = name_prod;
	}

	public String getSnActivo() {
		return snActivo;
	}

	public void setSnActivo(String snActivo) {
		this.snActivo = snActivo;
	}

	public String getStatus_prod() {
		if (snActivo.equals("N")) {
			status_prod = "0";
			
		} else {
			status_prod = "1";
		}
		return status_prod;
	}

	public void setStatus_prod(String status_prod) {
		
		if (status_prod.equals("1")) {
			this.snActivo = "S";
			
		} else {
			this.snActivo= "N";
		}
		this.status_prod = status_prod;
	}

	public Integer getQty_ean_unit() {
		return qty_ean_unit;
	}

	public void setQty_ean_unit(Integer qty_ean_unit) {
		this.qty_ean_unit = qty_ean_unit;
	}

	public Integer getQty_min_sell() {
		return qty_min_sell;
	}

	public void setQty_min_sell(Integer qty_min_sell) {
		this.qty_min_sell = qty_min_sell;
	}

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	public String getIndustry_code() {
		return industry_code;
	}

	public void setIndustry_code(String industry_code) {
		this.industry_code = industry_code;
	}

	public String getShort_description() {
		short_description = name_prod;
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public Float getDefault_price() {
		return default_price;
	}

	public void setDefault_price(Float default_price) {
		this.default_price = default_price;
	}

	public String getBrand() {
		
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
		
}
