package com.vgmsistemas.vgminterface.restController.unilever;

import java.util.List;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.unilever.Respuesta;
import com.vgmsistemas.vgminterface.entity.unilever.RespuestaOrden;
import com.vgmsistemas.vgminterface.service.unilever.RespuestaOrdenService;
import com.vgmsistemas.vgminterface.service.unilever.RespuestaService;
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
import com.vgmsistemas.vgminterface.util.RutasServicios;

@PreAuthorize("authenticated")
@RestController
@RequestMapping(RutasServicios.PEDIDOS_RESPUESTA)
public class OrdenRespRestController {
	private static Logger LOG = LoggerFactory.getLogger(OrdenRespRestController.class)	;
	
	@Autowired
	RespuestaOrdenService respuestaOrdenService;
	
	@PostMapping()
	public Integer ackOrden(@RequestBody String respuestaString) throws Exception {
		Gson gson = new Gson();
		JSONObject jsonProd = new JSONObject(respuestaString);
		LOG.info("Json recibido:" + respuestaString );
		RespuestaOrden respuesta = gson.fromJson(jsonProd.toString(), RespuestaOrden.class);
		
		try {
			LOG.info("OrdenRespRestController actualizarOrden() - Antes de marcar como enviada las ORDENES" );
			LOG.info("Json: " + respuestaString );
			respuestaOrdenService.actualizarEnvio(respuesta, "INT_ORDEN");
			LOG.info("OrdenRespRestController actualizarOrden() - Se actualizó correctamente las ORDENES" );
			return 1;
		}
		catch (Exception e) { 
			LOG.error("OrdenRespRestController createRespuesta()" + e.getMessage());
			throw e;
		}
		
	}
	
	
	
	@GetMapping()
	public List<RespuestaOrden> leerRespuesta() throws Exception {
				
		return respuestaOrdenService.leerTodos();
		
	}
	
}
