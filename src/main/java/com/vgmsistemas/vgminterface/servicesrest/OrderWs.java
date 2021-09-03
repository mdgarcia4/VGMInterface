package com.vgmsistemas.vgminterface.servicesrest;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.Orders;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.entity.unilever.Views;

public class OrderWs extends GenericWs {
	private static Logger LOG =  LoggerFactory.getLogger(OrderWs.class)	;
	public Integer callWebService(Orders orders, Optional<ParametroInterface> param, String tipo) throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		DecimalFormat decimalf = new DecimalFormat("#,###,##0.00");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);

		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		
		String jsonOrders;
		if (tipo.equals("COMPLETO")) {
			jsonOrders = mapper
				      .writerWithView(Views.Orden.class)
				      .writeValueAsString(orders);
			 LOG.info("Json Orden COMPLETA:" + jsonOrders);
		} else {
			jsonOrders = mapper
				      .writerWithView(Views.Public.class)
				      .writeValueAsString(orders);
			 LOG.info("Json Orden RESUMIDA:" + jsonOrders);
		}
		
	
		if (param.isPresent()) {
			url = param.get().getDeUrlEstadoVentas();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			
			LOG.info("OrderWs callWebService(): Envio de ordenes");
			LOG.info("URL:" + url);
			LOG.info("client_id:" + client_id);
			LOG.info("client_secret:" + client_secret);
			LOG.info("Json de Ordenes enviadas:" + jsonOrders);
			String response = this.callWebService(url,client_id ,client_secret,jsonOrders);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio OrderWs");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
	}
}
