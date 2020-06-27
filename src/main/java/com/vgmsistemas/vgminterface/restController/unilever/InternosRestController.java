package com.vgmsistemas.vgminterface.restController.unilever;

import com.vgmsistemas.vgminterface.service.unilever.CuentaClienteService;
import com.vgmsistemas.vgminterface.util.RutasServicios;

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@PreAuthorize("authenticated")
@RestController
public class InternosRestController {
	private static Logger LOG = LoggerFactory.getLogger(InternosRestController.class)	;
	
	@Autowired
	CuentaClienteService cuentaClienteService;
	
	@RequestMapping(value = RutasServicios.ENVIAR_ESTADO_CUENTA_CLIENTE, method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer enviarEstadoCuentaCliente(@RequestParam Integer id) throws Exception {
		try {
			// Primero Obtengo la info
			return cuentaClienteService.enviar(id);
		}
		catch (Exception e) { 
			LOG.error(e.getMessage());
			return -1;
		}
		
	}
}
