package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Views;

public class CuentaClienteWs extends GenericWs{
	public Integer callWebService(Optional<CuentaCliente> cuentaCliente, Optional<ParametroInterface> param) throws Exception {
		/*Gson gson = new Gson();
		String jsonCliente = gson.toJsonTree(cuentaCliente).getAsJsonObject().toString();*/
        //parametros.put("clienteJson", jsonCliente.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
	    String jsonCliente = mapper
	      .writerWithView(Views.Request.class)
	      .writeValueAsString(cuentaCliente.get());
		
		if (param.isPresent()) {
			url = param.get().getDeUrlCuentaClienteEstado();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			String response = this.callWebService(url,client_id ,client_secret,jsonCliente);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio RetailerStatus");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
			
		
		
	}

	public CuentaClienteWs() {
		super();
		
	}

	

}
