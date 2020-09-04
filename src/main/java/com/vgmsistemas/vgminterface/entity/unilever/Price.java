
package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "store_id_ERP","retailer_group","price","special_price","special_price_start_date","special_price_end_date",
	"discount_value","discount_type","quantity_min_product","quantity_max_product","valid_week_day"})
@Table( name = "v_precios_interface")
public class Price{

	
	@Id
	private PkPrice id;
	
	
	@Column(name="id_codigobarras")
	private String EAN;
	
	@Column(name = "id_proveedor" )
	private Integer idProveedor;
	
	@JsonView(Views.Precio.class)
	@Column(name = "store_id_ERP")
	private String store_id_ERP;
	
	@JsonView(Views.Precio.class)
	@Column(name = "price")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "##,###,##0.00" )
	private Float price;
	
	@JsonView(Views.Precio.class)
	@Column(name = "special_price")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "##,###,##0.00" )
	private Float special_price;
	
	@JsonView(Views.Precio.class)
	@Column(name = "special_price_start_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date special_price_start_date;
	
	@JsonView(Views.Precio.class)
	@Column(name = "special_price_end_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING )
	private Date special_price_end_date;
	
	@JsonView(Views.Precio.class)
	@Column(name="discount_value")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "##,###,##0.00" )
	private Float discount_value;
	
	@JsonView(Views.Precio.class)
	@Column(name="discount_type")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer discount_type;
	
	@JsonView(Views.Precio.class)
	@Column(name="quantity_min_product")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer quantity_min_product;
	
	@JsonView(Views.Precio.class)
	@Column(name="quantity_max_product")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer quantity_max_product;
	
	@JsonView(Views.Precio.class)
	@Column(name="valid_week_day")
	private String valid_week_day;
	
	@JsonView(Views.Precio.class)
	@Column(name="retailer_group")
	private String retailer_group;
	
	/*@ManyToOne
    @JoinColumn(name = "id_articulos" , insertable = false, updatable = false)*/
	
	@ManyToOne()
	@JoinColumn(name = "id_articulos", referencedColumnName= "id_articulos", insertable = false, updatable = false)
	    private Product product;
	
	public PkPrice getId() {
		return id;
	}

	public void setId(PkPrice id) {
		this.id = id;
	}

	public String getEAN() {
		return EAN.trim();
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSpecial_price() {
		return special_price;
	}

	public void setSpecial_price(Float special_price) {
		this.special_price = special_price;
	}

	public Float getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(Float discount_value) {
		this.discount_value = discount_value;
	}

	public Integer getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(Integer discount_type) {
		this.discount_type = discount_type;
	}

	public Date getSpecial_price_start_date() {
		return special_price_start_date;
	}

	public void setSpecial_price_start_date(Date special_price_start_date) {
		this.special_price_start_date = special_price_start_date;
	}

	

	public Date getSpecial_price_end_date() {
		return special_price_end_date;
	}

	public void setSpecial_price_end_date(Date special_price_end_date) {
		this.special_price_end_date = special_price_end_date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity_min_product() {
		return quantity_min_product;
	}

	public void setQuantity_min_product(Integer quantity_min_product) {
		this.quantity_min_product = quantity_min_product;
	}

	public Integer getQuantity_max_product() {
		return quantity_max_product;
	}

	public void setQuantity_max_product(Integer quantity_max_product) {
		this.quantity_max_product = quantity_max_product;
	}

	public String getValid_week_day() {
		return valid_week_day;
	}

	public void setValid_week_day(String valid_week_day) {
		this.valid_week_day = valid_week_day;
	}

	public String getRetailer_group() {
		return retailer_group;
	}

	public void setRetailer_group(String retailer_group) {
		this.retailer_group = retailer_group;
	}

	public String getStore_id_ERP() {
		return store_id_ERP;
	}

	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
	}

	

			
}
