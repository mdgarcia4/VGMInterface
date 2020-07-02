package com.vgmsistemas.vgminterface.util;

public class RutasServicios {
	public static final String BASE_WS = "${DIRECTORIO_RAIZ:/VGMInterface}";
	public static final String CUENTA_CLIENTE = BASE_WS + "/Retailer";
	public static final String SERVICE_AUTORIZATION = "";
	public static final String ENVIAR_ESTADO_CUENTA_CLIENTE = BASE_WS +"/enviarEstadoCuentaCliente";
	public static final String ENVIAR_PRODUCTOS = BASE_WS +"/enviarProductos";
	public static final String PRODUCTOS_RESPUESTA = BASE_WS +"/Catalog/acknowledgement";
}