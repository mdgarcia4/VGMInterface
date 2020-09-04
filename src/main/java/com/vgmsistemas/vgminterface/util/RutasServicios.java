package com.vgmsistemas.vgminterface.util;

public class RutasServicios {
	public static final String BASE_WS = "${DIRECTORIO_RAIZ:/VGMInterface}";
	public static final String SERVICE_AUTORIZATION = "";
	
	// Ingresos desde compre ahora
	public static final String CUENTA_CLIENTE = BASE_WS + "/Retailer";
	public static final String PEDIDO = BASE_WS + "/SalesOrder";
	public static final String PEDIDO_ACTUALIZA = BASE_WS + "/SalesOrder/Update";
	
	// Envios a COMPRE AHORA
	public static final String ENVIAR_ESTADO_CUENTA_CLIENTE = BASE_WS +"/enviarEstadoCuentaCliente";
	public static final String ENVIAR_PRODUCTOS = BASE_WS +"/enviarProductos";
	public static final String ENVIAR_PRODUCTOS_LOTE = BASE_WS +"/enviarProductosLote";
	public static final String ENVIAR_PRODUCTOS_ACTUALIZADOS = BASE_WS +"/enviarProductosActualizados";
	public static final String ENVIAR_PRODUCTOS_ACTUALIZADOS_LOTE = BASE_WS +"/enviarProductosActualizadosLote";
	public static final String ENVIAR_PRODUCTO = BASE_WS +"/enviarProducto";
	public static final String ENVIAR_PRECIOS = BASE_WS +"/enviarPrecios";
	public static final String ENVIAR_PRECIOS_LOTE = BASE_WS +"/enviarPreciosLote";
	public static final String ENVIAR_PRECIOS_ACTUALIZADOS = BASE_WS +"/enviarPreciosActualizados";
	public static final String ENVIAR_PRECIOS_ACTUALIZADOS_LOTE = BASE_WS +"/enviarPreciosActualizadosLote";
	public static final String ENVIAR_PRECIOS_PRODUCTO = BASE_WS +"/enviarPreciosProducto";
	public static final String ENVIAR_STOCK = BASE_WS +"/enviarStock";
	public static final String ENVIAR_STOCK_lOTE = BASE_WS +"/enviarStockLote";
	public static final String ENVIAR_STOCK_PRODUCTO = BASE_WS +"/enviarStockProducto";
	public static final String ENVIAR_PEDIDOS= BASE_WS +"/enviarPedidos";
	
	// Respuestas de COMPRE AHORA
	public static final String CUENTA_CLIENTE_RESPUESTA = BASE_WS + "/Retailer/acknowledgement";
	public static final String PRODUCTOS_RESPUESTA = BASE_WS +"/Catalog/acknowledgement";
	public static final String PRECIOS_RESPUESTA = BASE_WS +"/Prices/acknowledgement";
	public static final String STOCK_RESPUESTA = BASE_WS +"/Inventory/acknowledgement";
	public static final String PEDIDOS_RESPUESTA = BASE_WS +"/SalesOrder/acknowledgement";
	
	// Recuperar
	public static final String GET_PRODUCTO = BASE_WS +"/getProducto";
	public static final String GET_PRODUCTOS = BASE_WS +"/getProductos";
	
	
	
	
	
	
	
}