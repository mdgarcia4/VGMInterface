package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Views;


public class CuentaClienteWs extends GenericWs{
	private static Logger LOG =  LoggerFactory.getLogger(CuentaClienteWs.class)	;
	public Integer callWebService(Optional<CuentaCliente> cuentaCliente, Optional<ParametroInterface> param) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	    String jsonCliente = mapper
	      .writerWithView(Views.Request.class)
	      .writeValueAsString(cuentaCliente.get());
		
		if (param.isPresent()) {
			String response;
			url = param.get().getDeUrlCuentaClienteEstado();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			LOG.info("CuentaClienteWs callWebService(). Se llama al servicio Web : " + url );
			LOG.info("Json: " + jsonCliente);
			try {
				response = this.callWebService(url,client_id ,client_secret,jsonCliente);
			} catch (Exception e) {
				LOG.error("CuentaClienteWs callWebService(). Error al llamar al servicio Web: " + url );
				LOG.error("Json: " + jsonCliente);
				throw e;
			}
			
			if (!response.equals("0")) {
				LOG.error("CuentaClienteWs callWebService(). Error al llamar a servicio RetailerStatus");
				throw new Exception("CuentaClienteWs callWebService(). Error al llamar a servicio RetailerStatus");
			}
			return Integer.valueOf(response);
		} else {
			LOG.error("CuentaClienteWs callWebService(). Error al buscar parámetros en tabla ParametrosInterface");
			throw new Exception("CuentaClienteWs callWebService(). Error al buscar parámetros en tabla ParametrosInterface");
		}
			
		
		
	}

	public CuentaClienteWs() {
		super();
		
	}

	

}
