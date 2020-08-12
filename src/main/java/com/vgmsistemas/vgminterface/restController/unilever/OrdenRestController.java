package com.vgmsistemas.vgminterface.restController.unilever;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vgmsistemas.vgminterface.entity.unilever.Orders;
import com.vgmsistemas.vgminterface.entity.unilever.OrdersResp;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.service.unilever.OrderService;
import com.vgmsistemas.vgminterface.util.RutasServicios;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(RutasServicios.PEDIDO)
public class OrdenRestController {
	private static Logger LOG = LoggerFactory.getLogger(OrdenRestController.class)	;
	
	@Autowired
	OrderService orderService;
		
	@PostMapping()
	@JsonView(Views.Response.class)
	public String createPedido(@RequestBody String pedidoString) throws Exception {
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JSONObject jsonPedido = new JSONObject(pedidoString);
		Orders orders = gson.fromJson(jsonPedido.toString(), Orders.class);
		Orders ordersDef = gson.fromJson(jsonPedido.toString(), Orders.class);
		
		try {
			LOG.info("createPedido" );
			LOG.info("Json Pedido Original: " + pedidoString);
			orders = orderService.tratar(orders,"ORIGINAL");
			LOG.info("Pedido original creado OK " );
			
			LOG.info("Json Pedido Definitivo: " + pedidoString);
			orderService.tratar(ordersDef,"DEFINITIVA");
			LOG.info("Pedido Definitivo creado OK " );
			
			OrdersResp orderResp = new OrdersResp(orders.getOrders());
			orderResp.setDistributor_code(orders.getDistributor_code());
						
			ObjectMapper mapper = new ObjectMapper();

			mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		    String jsonRespuesta = mapper
		      .writerWithView(Views.Public.class)
		      .writeValueAsString(orderResp);
			
		    LOG.info("Json Respuesta: " + jsonRespuesta);
			
			return jsonRespuesta;
		}
		catch (Exception e) { 
			LOG.error("CuentaCliente createCuentaCliente() . " + e.getMessage());
			throw e;
		}
		
	}
	
	/*@GetMapping()
	public List<CuentaCliente> leerCuentaCliente() throws Exception {
		LOG.info("CuentaCliente leerCuentaCliente() . Antes de llamar a leerCuentaCliente(cuentaCliente)" );		
		return cuentaClienteService.leerTodos();
		
	}*/
	
}
