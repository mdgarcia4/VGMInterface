package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;

public class CuentaClienteWs extends GenericWs{
	public Integer callWebService(Optional<CuentaCliente> cuentaCliente, Optional<ParametroInterface> param) throws Exception {
		Gson gson = new Gson();
		String jsonCliente = gson.toJsonTree(cuentaCliente).getAsJsonObject().toString();
        //parametros.put("clienteJson", jsonCliente.toString());
		
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
