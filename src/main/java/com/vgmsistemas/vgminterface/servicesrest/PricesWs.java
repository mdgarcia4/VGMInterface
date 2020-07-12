package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.Prices;
import com.vgmsistemas.vgminterface.entity.unilever.Views;

public class PricesWs extends GenericWs {
	private static Logger LOG =  LoggerFactory.getLogger(PricesWs.class)	;
	public Integer callWebService(Prices precios, Optional<ParametroInterface> param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	    String jsonPrrecios = mapper
	      .writerWithView(Views.Precio.class)
	      .writeValueAsString(precios);
		
	
		if (param.isPresent()) {
			url = param.get().getDeUrlPrecio();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			
			LOG.info("ProductsWs callWebService(): Envio de precios");
			LOG.info("URL:" + url);
			LOG.info("client_id:" + client_id);
			LOG.info("client_secret:" + client_secret);
			LOG.info("Json de Prrecios enviados:" + jsonPrrecios);
			String response = this.callWebService(url,client_id ,client_secret,jsonPrrecios);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio Precis");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
	}
}
