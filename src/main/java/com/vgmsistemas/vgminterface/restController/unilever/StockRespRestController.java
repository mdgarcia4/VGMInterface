package com.vgmsistemas.vgminterface.restController.unilever;

import java.util.List;
import com.google.gson.Gson;
import com.vgmsistemas.vgminterface.entity.unilever.Respuesta;
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
@RequestMapping(RutasServicios.STOCK_RESPUESTA)
public class StockRespRestController {
	private static Logger LOG = LoggerFactory.getLogger(StockRespRestController.class)	;
	
	@Autowired
	RespuestaService respuestaService;
	
	@PostMapping()
	public Integer createRespuesta(@RequestBody String respuestaString) throws Exception {
		Gson gson = new Gson();
		JSONObject jsonStock = new JSONObject(respuestaString);
		Respuesta respuesta = gson.fromJson(jsonStock.toString(), Respuesta.class);
		
		try {
			LOG.info("PreciosRespRestController createRespuesta() - Antes de crear la Respuesta de STOCK" );
			LOG.info("Json: " + respuestaString );
			respuestaService.crear(respuesta, "STOCK");
			LOG.info("PreciosRespRestController createRespuesta() - Creó correctamente la Respuesta de STOCK" );
			return 1;
		}
		catch (Exception e) { 
			LOG.error("RespuestaRestController createRespuesta()" + e.getMessage());
			throw e;
		}
		
	}
	
	
	
	@GetMapping()
	public List<Respuesta> leerRespuesta() throws Exception {
				
		return respuestaService.leerTodos();
		
	}
	
}
