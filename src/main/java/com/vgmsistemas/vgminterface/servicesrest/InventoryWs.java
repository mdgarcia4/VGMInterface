package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.Inventories;
import com.vgmsistemas.vgminterface.entity.unilever.Views;

public class InventoryWs extends GenericWs {
	private static Logger LOG =  LoggerFactory.getLogger(InventoryWs.class)	;
	public Integer callWebService(Inventories inventarios, Optional<ParametroInterface> param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	    String jsonInventarios = mapper
	      .writerWithView(Views.Inventory.class)
	      .writeValueAsString(inventarios);
		
	
		if (param.isPresent()) {
			url = param.get().getDeUrlStock();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			
			LOG.info("ProductsWs callWebService(): Envio de inventario");
			LOG.info("URL:" + url);
			LOG.info("client_id:" + client_id);
			LOG.info("client_secret:" + client_secret);
			LOG.info("Json de Inventarios enviados:" + jsonInventarios);
			String response = this.callWebService(url,client_id ,client_secret,jsonInventarios);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio Inventario");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
	}
}
