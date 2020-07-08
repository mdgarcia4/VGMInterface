package com.vgmsistemas.vgminterface.restController.unilever;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Views;
import com.vgmsistemas.vgminterface.service.unilever.CuentaClienteService;
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
@RequestMapping(RutasServicios.CUENTA_CLIENTE_RESPUESTA)
public class CuentaClienteRespRestController {
	private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	@Autowired
	CuentaClienteService cuentaClienteService;
	
		
	@PostMapping()
	@JsonView(Views.Response.class)
	public CuentaCliente respuestaCuentaCliente(@RequestBody String clieString) throws Exception {
		Gson gson = new Gson();
		JSONObject jsonClie = new JSONObject(clieString);
		LOG.info(clieString);
		CuentaCliente cuentaCliente = gson.fromJson(jsonClie.toString(), CuentaCliente.class);
		
		cuentaCliente = cuentaClienteService.actualizarEnviado(cuentaCliente);
		return cuentaCliente;
	}
	
		
}