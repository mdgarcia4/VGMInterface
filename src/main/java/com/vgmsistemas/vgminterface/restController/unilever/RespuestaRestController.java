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
@RequestMapping(RutasServicios.PRODUCTOS_RESPUESTA)
public class RespuestaRestController {
	private static Logger LOG = LoggerFactory.getLogger(RespuestaRestController.class)	;
	
	@Autowired
	RespuestaService respuestaService;
	
	@PostMapping()
	public Integer createRespuesta(@RequestBody String respuestaString) throws Exception {
		Gson gson = new Gson();
		JSONObject jsonProd = new JSONObject(respuestaString);
		Respuesta respuesta = gson.fromJson(jsonProd.toString(), Respuesta.class);
		
		try {
			respuestaService.crear(respuesta, "PRODUCTOS");
			return 1;
		}
		catch (Exception e) { 
			LOG.error(e.getMessage());
			return 0;
		}
		
	}
	
	
	
	@GetMapping()
	public List<Respuesta> leerRespuesta() throws Exception {
				
		return respuestaService.leerTodos();
		
	}
	
}
