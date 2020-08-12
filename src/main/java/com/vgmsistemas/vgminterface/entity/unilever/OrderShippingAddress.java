package com.vgmsistemas.vgminterface.entity.unilever;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden_envio_direccion" )
@JsonPropertyOrder({ "email","firstname","lastname","postcode","street","street_number",
					 "district","province","country","neighborhood","phone_number"})
public class OrderShippingAddress{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_nombre")
	private String firstname;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_apellido")
	private String lastname;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_email")
	private String email;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_calle")
	private String street;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_altura")
	private String street_number;
	
	@JsonView(Views.Orden.class)
	@Column(name = "cd_postal")
	private String postcode;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_provincia")
	private String province;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_departamento")
	private String district;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_localidad")
	private String neighborhood;
	
	@JsonView(Views.Orden.class)
	@Column(name = "de_pais")
	private String country;
	
	@JsonView(Views.Orden.class)
	@Column(name = "nu_telefono")
	private String phone_number;
		
	@OneToOne
    @JoinColumn(name = "id_envio")
    private OrderShipping shipping;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFistname() {
		return firstname;
	}

	public void setFistname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public OrderShipping getShipping() {
		return shipping;
	}

	public void setShipping(OrderShipping shipping) {
		this.shipping = shipping;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	

	
}
