package com.vgmsistemas.vgminterface.entity.unilever;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table( name = "int_orden" )
@JsonPropertyOrder({ "order_source","order_id","store_id_ERP","erp_order_id","updated_at","total_qty_ordered", 
	"discount_amount","grand_total","subtotal","total_item_count",
	"total_due","status","items","items_changed","payment","shipping","shipment_type",
	"payment_pending_amount","cancellation_reason","order_delivered_at"})
public class Order{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_codigo_distribuidor")
	private String distributor_code;
	
	@JsonView(Views.Public.class)
	@Column(name = "id_orden")
	private String order_id;
	
	@JsonView(Views.Orden.class)
	@Column(name = "id_cliente_tienda")
	private String store_id_ERP;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "fe_orden")
	private Date created_at;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "ca_total")
	private Float total_qty_ordered;
	
	
	@Column(name = "de_cliente_mail")
	private String customer_email;
	
	
	@Column(name = "de_cliente_nombre")
	private String customer_firstname;
	
	
	@Column(name = "de_cliente_apellido")
	private String customer_lastname;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "ca_items")
	private Integer total_item_count;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_subtotal_sdto")
	private Float subtotal;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_descuento")
	private Float discount_amount;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_total")
	private Float grand_total;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "pr_subtotal_cdto")
	private Float total_due;
	
	
	@Column(name = "de_direccion_ip")
	private String remote_ip;
	
	@JsonView(Views.Public.class)
	@Column(name = "ti_estado")
	private String status;
	
	@JsonView(Views.Orden.class)
	@Column(name = "ti_envio")
	private String shipment_type;
	
	@JsonView(Views.Orden.class)
	@OneToMany(mappedBy = "order", cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<OrderItem> items;
	
	@JsonView(Views.Orden.class)
	@OneToOne(mappedBy = "order1", cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	private OrderPayment payment;
	
	@JsonView(Views.Orden.class)
	@OneToOne(mappedBy = "order2", cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	private OrderShipping shipping;
	
	@OneToMany(mappedBy = "order3", cascade=CascadeType.PERSIST)
	private List<OrderItemChanged> items_cambiados;
	
	@JsonView(Views.Orden.class)
	@Transient
	private Map<String, String> items_changed;
	
	@Column(name = "ti_orden")
	private String tiOrden;
	
	@JsonView(Views.Orden.class)
	@Column(name ="ti_origen")
	private String order_source;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name="fe_modificacion")
	private Date updated_at;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name="pr_pendiente_pago")
	private Float payment_pending_amount;
	
	@JsonView(Views.Orden.class)
	@Column(name="de_motivo_cancelacion")
	private String cancellation_reason;
	
	@JsonView(Views.Orden.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name="fe_entrega_orden")
	private Date order_delivered_at;
	
	@Column(name="sn_estado_enviado")
	private String snEstadoEnviado;
	
	@JsonView(Views.Orden.class)
	@Column(name="id_orden_ERP")
	private String erp_order_id;
	
	@Column(name="id_fcnc")
	private String idFcnc;
	
	@Column(name="id_tipoab")
	private String idTipoab;
	
	@Column(name="id_ptovta")
	private Integer idPtovta;
	
	@Column(name="id_numdoc")
	private Integer idNumdoc;
	
	@Column(name="sn_generado")
	private String snGenerado;
	
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

	public String getStore_id_ERP() {
		return store_id_ERP;
	}

	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Float getTotal_qty_ordered() {
		return total_qty_ordered;
	}

	public void setTotal_qty_ordered(Float total_qty_ordered) {
		this.total_qty_ordered = total_qty_ordered;
	}

	public String getCustomer_firstname() {
		return customer_firstname;
	}

	public void setCustomer_firstname(String customer_firstname) {
		this.customer_firstname = customer_firstname;
	}

	public String getCustomer_lastname() {
		return customer_lastname;
	}

	public void setCustomer_lastname(String customer_lastname) {
		this.customer_lastname = customer_lastname;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public Integer getTotal_item_count() {
		return total_item_count;
	}

	public void setTotal_item_count(Integer total_item_count) {
		this.total_item_count = total_item_count;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(Float discount_amount) {
		this.discount_amount = discount_amount;
	}

	public Float getTotal_due() {
		return total_due;
	}

	public void setTotal_due(Float total_due) {
		this.total_due = total_due;
	}

	public Float getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(Float grand_total) {
		this.grand_total = grand_total;
	}

	public String getRemote_ip() {
		return remote_ip;
	}

	public void setRemote_ip(String remote_ip) {
		this.remote_ip = remote_ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public String getShipment_type() {
		return shipment_type;
	}

	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public OrderPayment getPayment() {
		return payment;
	}

	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}

	public OrderShipping getShipping() {
		return shipping;
	}

	public void setShipping(OrderShipping shipping) {
		this.shipping = shipping;
	}

	public String getTiOrden() {
		return tiOrden;
	}

	public void setTiOrden(String tiOrden) {
		this.tiOrden = tiOrden;
	}

	public String getOrder_source() {
		return order_source;
	}

	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Float getPayment_pending_amount() {
		return payment_pending_amount;
	}

	public void setPayment_pending_amount(Float payment_pending_amount) {
		this.payment_pending_amount = payment_pending_amount;
	}

	public String getCancellation_reason() {
		return cancellation_reason;
	}

	public void setCancellation_reason(String cancellation_reason) {
		this.cancellation_reason = cancellation_reason;
	}

	public Date getOrder_delivered_at() {
		return order_delivered_at;
	}

	public void setOrder_delivered_at(Date order_delivered_at) {
		this.order_delivered_at = order_delivered_at;
	}

	public String getSnEstadoEnviado() {
		return snEstadoEnviado;
	}

	public void setSnEstadoEnviado(String snEstadoEnviado) {
		this.snEstadoEnviado = snEstadoEnviado;
	}

	public String getErp_order_id() {
		return erp_order_id;
	}

	public void setErp_order_id(String erp_order_id) {
		this.erp_order_id = erp_order_id;
	}

	public String getIdFcnc() {
		return idFcnc;
	}

	public void setIdFcnc(String idFcnc) {
		this.idFcnc = idFcnc;
	}

	public String getIdTipoab() {
		return idTipoab;
	}

	public void setIdTipoab(String idTipoab) {
		this.idTipoab = idTipoab;
	}

	public Integer getIdPtovta() {
		return idPtovta;
	}

	public void setIdPtovta(Integer idPtovta) {
		this.idPtovta = idPtovta;
	}

	public Integer getIdNumdoc() {
		return idNumdoc;
	}

	public void setIdNumdoc(Integer idNumdoc) {
		this.idNumdoc = idNumdoc;
	}


	public Map<String, String> getItems_changed() {
		return items_changed;
	}

	public void setItems_changed(Map<String, String> items_changed) {
		this.items_changed = items_changed;
	}

	public List<OrderItemChanged> getItems_cambiados() {
		return items_cambiados;
	}

	public void setItems_cambiados(List<OrderItemChanged> items_cambiados) {
		this.items_cambiados = items_cambiados;
	}

	public String getSnGenerado() {
		return snGenerado;
	}

	public void setSnGenerado(String snGenerado) {
		this.snGenerado = snGenerado;
	}

	
	
	
}
