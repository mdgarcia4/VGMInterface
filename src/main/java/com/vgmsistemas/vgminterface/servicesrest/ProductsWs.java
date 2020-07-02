package com.vgmsistemas.vgminterface.servicesrest;

import java.util.Optional;

import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.Products;

public class ProductsWs extends GenericWs {
	public Integer callWebService(Products productos, Optional<ParametroInterface> param) throws Exception {
		Gson gson = new Gson();
		String jsonProductos = gson.toJsonTree(productos).getAsJsonObject().toString();
        //parametros.put("clienteJson", jsonCliente.toString());
		
		if (param.isPresent()) {
			url = param.get().getDeUrlCatalogo();
			String client_id = param.get().getClient_id();
			String client_secret = param.get().getClient_secret();
			String response = this.callWebService(url,client_id ,client_secret,jsonProductos);
			if (!response.equals("0")) throw new Exception("Error al llamar a servicio Products");
			return Integer.valueOf(response);
		} else {
			throw new Exception("Error al buscar parámetros en tabla ParametrosInterface");
		}
	}
}
