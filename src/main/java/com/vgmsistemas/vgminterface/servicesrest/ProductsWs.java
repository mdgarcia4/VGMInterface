package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.entity.unilever.Views;

public class ProductsWs extends GenericWs {
	private static Logger LOG =  LoggerFactory.getLogger(ProductsWs.class)	;
	public Integer callWebService(Products productos, Optional<ParametroInterface> param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	    String jsonProductos = mapper
	      .writerWithView(Views.Producto.class)
	      .writeValueAsString(productos);
		
		
		//Gson gson = new Gson();
		//String jsonProductos = gson.toJsonTree(productos).getAsJsonObject().toString();
        //parametros.put("clienteJson", jsonCliente.toString());
		
		if (param.isPresent()) {
			url = param.get().getDeUrlCatalogo();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			
			LOG.info("ProductsWs callWebService(): Envio de productos");
			LOG.info("URL:" + url);
			LOG.info("client_id:" + client_id);
			LOG.info("client_secret:" + client_secret);
			LOG.info("Json de Productos enviados:" + jsonProductos);
			String response = this.callWebService(url,client_id ,client_secret,jsonProductos);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio Products");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
	}
}
