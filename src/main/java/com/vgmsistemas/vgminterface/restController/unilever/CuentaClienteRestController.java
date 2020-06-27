package com.vgmsistemas.vgminterface.restController.unilever;

import java.util.List;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.service.unilever.CuentaClienteService;
import com.vgmsistemas.vgminterface.util.RutasServicios;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("authenticated")
@RestController
@RequestMapping(RutasServicios.CUENTA_CLIENTE)
public class CuentaClienteRestController {
	private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	@Autowired
	CuentaClienteService cuentaClienteService;
	
	/*@PostMapping()
	public CuentaCliente createCuentaCliente(@RequestBody CuentaCliente cuentaCliente) throws Exception {
		 
		try {
			CuentaCliente ctaclie = cuentaClienteService.tratarCuenta(cuentaCliente);
			return ctaclie;
		}
		catch (Exception e) { 
			LOG.error(e.getMessage());
			return null;
		}
		
	}*/
	
	@PostMapping()
	public CuentaCliente createCuentaCliente(@RequestBody String clieString) throws Exception {
		Gson gson = new Gson();
		JSONObject jsonClie = new JSONObject(clieString);
		CuentaCliente cuentaCliente = gson.fromJson(jsonClie.toString(), CuentaCliente.class);
		
		try {
			CuentaCliente ctaclie = cuentaClienteService.tratarCuenta(cuentaCliente);
			return ctaclie;
		}
		catch (Exception e) { 
			LOG.error(e.getMessage());
			return null;
		}
		
	}
	
	@GetMapping()
	public List<CuentaCliente> leerCuentaCliente() throws Exception {
				
		return cuentaClienteService.leerTodos();
		
	}
	
}
