package com.vgmsistemas.vgminterface.restController.unilever;

import com.fasterxml.jackson.annotation.JsonView;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.service.unilever.CuentaClienteService;
import com.vgmsistemas.vgminterface.service.unilever.InventoryService;
import com.vgmsistemas.vgminterface.service.unilever.OrderService;
import com.vgmsistemas.vgminterface.service.unilever.ProductsService;
import com.vgmsistemas.vgminterface.entity.unilever.Product;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.util.RutasServicios;
import org.springframework.http.MediaType;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("authenticated")
@RestController
public class InternosRestController {
	private static Logger LOG = LoggerFactory.getLogger(InternosRestController.class)	;
	
	@Autowired
	CuentaClienteService cuentaClienteService;
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	OrderService orderService;
	
	@JsonView(Views.Request.class)
	@RequestMapping(value = RutasServicios.ENVIAR_ESTADO_CUENTA_CLIENTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarEstadoCuentaCliente(@RequestParam (required = false) Integer id) throws Exception {
		try {
			Integer i;
			// Primero Obtengo la info
			LOG.info("Se envia el retailer Nro: " + id);
			
			if (id==null) {
				i = cuentaClienteService.enviar();
			} else {
				i = cuentaClienteService.enviar(id);
			}
			
			return i;
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarEstdoCuentaCliente(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRODUCTOS, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarProductos() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviar("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRODUCTOS_LOTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarProductosLote() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarProductosxLote("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductosLote(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRODUCTOS_ACTUALIZADOS, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarProductosActualizados() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviar("ACTUALIZADOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductosActualizados(). Error: " + e.getMessage());
			throw e;
		}
		
	}	
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRODUCTOS_ACTUALIZADOS_LOTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarProductosActualizadosLote() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarProductosxLote("ACTUALIZADOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductosActualizadosLote(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRODUCTO, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarProducto(String ean) throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarEAN(ean);
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
		
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRECIOS, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPrecios() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarPrecios("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRECIOS_LOTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPreciosLote() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarPreciosxLote("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRECIOS_ACTUALIZADOS, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPreciosActualizados() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarPrecios("ACTUALIZADOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarPreciosActualizados(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRECIOS_ACTUALIZADOS_LOTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPreciosActualizadosLote() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarPreciosxLote("ACTUALIZADOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarPreciosActualizadosLote(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PRECIOS_PRODUCTO, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPrecio(String ean) throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.enviarPreciosProducto(ean);
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_STOCK, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarStock() throws Exception {
		try {
			// Primero Obtengo la info
			return inventoryService.enviar("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_STOCK_lOTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarStockLote() throws Exception {
		try {
			// Primero Obtengo la info
			return inventoryService.enviarxLote("TODOS");
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.ENVIAR_STOCK_PRODUCTO, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarStockProducto(String ean) throws Exception {
		try {
			// Primero Obtengo la info
			return inventoryService.enviarEAN(ean);
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = RutasServicios.ENVIAR_PEDIDOS, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarPedidos() throws Exception {
		try {
			// Primero Obtengo la info
			return orderService.enviar();
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarOrden(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.GET_PRODUCTOS, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Products getProductos() throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.leerProductos();
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
	
	@JsonView(Views.Producto.class)
	@RequestMapping(value = RutasServicios.GET_PRODUCTO, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> getProductos(Long id) throws Exception {
		try {
			// Primero Obtengo la info
			return productsService.leerProductos(id);
		}
		catch (Exception e) { 
			LOG.error("InternosRestController enviarProductos(). Error: " + e.getMessage());
			throw e;
		}
		
	}
}
