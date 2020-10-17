package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "cuenta_cliente" )
@JsonPropertyOrder({ "distributor_code", "property_name", "store_id_ERP", "Store_Status",
	"rejection_reason","erp_seller","cuit_dni_id","document_type_id","ingresos_brutos","customer_type",
	"street","number","Neighborhood","District","Province","country","postal_code","phone_number",
	"mobile_number","email","firstname","surname","customer_credit_available",
	"customer_total_credit","customer_pending_payment"})
public class CuentaCliente{
	
	@Id
	@Column(name = "id_cuenta_cliente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@Column(name = "id_codigo_distribuidor")
	private String distributor_code;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_cuenta_cliente")
	private String property_name;
	
	@JsonView(Views.Public.class)
	@Column(name = "id_cuit_dni")
	private String cuit_dni_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "ti_documento")
	private String document_type_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "id_ingresos_brutos")
	private String ingresos_brutos;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_canal_prov")
	private String customer_type;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_calle")
	private String street;
	
	@JsonView(Views.Public.class)
	@Column(name = "nu_calle")
	private String number;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_barrio")
	private String Neighborhood;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_departamento")
	private String District;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_provincia")
	private String Province;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_pais")
	private String country;
	
	@JsonView(Views.Public.class)
	@Column(name = "cd_postal_nvo")
	private String postal_code;
	
	@JsonView(Views.Public.class)
	@Column(name = "nu_telefono")
	private String phone_number;
	
	@JsonView(Views.Public.class)
	@Column(name = "nu_celular")
	private String mobile_number;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_email")
	private String email;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_nombre_representante")
	private String firstname;
	
	@JsonView(Views.Public.class)
	@Column(name = "de_apellido_representante")
	private String surname;
	
	@JsonView(Views.Public.class)
	@Column(name = "id_cliente_tienda")
	private String store_id_ERP;
	
	@Column(name = "id_sucursal")
	private Long idSucursal;
	
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "id_comercio")
	private Long idComercio;
	
	@JsonView(Views.Response.class)
	@Column(name = "id_estado")
	private String Store_Status;
	
	@JsonView(Views.Response.class)
	@Column(name = "de_motivo_rechazo")
	private String rejection_reason;
	
	@JsonView(Views.Request.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_credito_disponible")
	private Float customer_credit_available = (float) 0.0 ;
	
	@JsonView(Views.Request.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_credito_total")
	private Float customer_total_credit= (float) 0.0;
	
	@JsonView(Views.Request.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_monto_adeudado")
	private Float customer_pending_payment= (float) 0.0;
	
	@JsonView(Views.Request.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "id_vendedor")
	private Integer erp_seller;
	
	@Column(name = "sn_enviado")
	private char sn_enviado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getCuit_dni_id() {
		return cuit_dni_id;
	}

	public void setCuit_dni_id(String cuit_dni_id) {
		this.cuit_dni_id = cuit_dni_id;
	}

	public String getDocument_type_id() {
		return document_type_id;
	}

	public void setDocument_type_id(String document_type_id) {
		this.document_type_id = document_type_id;
	}

	public String getIngresos_brutos() {
		return ingresos_brutos;
	}

	public void setIngresos_brutos(String ingresos_brutos) {
		this.ingresos_brutos = ingresos_brutos;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return Neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		Neighborhood = neighborhood;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStore_id_ERP() {
		return store_id_ERP;
	}

	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
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

	public String getStore_Status() {
		return Store_Status;
	}

	public void setStore_Status(String store_Status) {
		Store_Status = store_Status;
	}

	public String getRejection_reason() {
		return rejection_reason;
	}

	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}

	public Float getCustomer_credit_available() {
		return customer_credit_available;
	}

	public void setCustomer_credit_available(Float customer_credit_available) {
		this.customer_credit_available = customer_credit_available;
	}

	public Float getCustomer_total_credit() {
		return customer_total_credit;
	}

	public void setCustomer_total_credit(Float customer_total_credit) {
		this.customer_total_credit = customer_total_credit;
	}

	public Float getCustomer_pending_payment() {
		return customer_pending_payment;
	}

	public void setCustomer_pending_payment(Float customer_pending_payment) {
		this.customer_pending_payment = customer_pending_payment;
	}

	public Integer getErp_seller() {
		return erp_seller;
	}

	public void setErp_seller(Integer erp_seller) {
		this.erp_seller = erp_seller;
	}

	public char getSn_enviado() {
		return sn_enviado;
	}

	public void setSn_enviado(char sn_enviado) {
		this.sn_enviado = sn_enviado;
	}

		
	
}
