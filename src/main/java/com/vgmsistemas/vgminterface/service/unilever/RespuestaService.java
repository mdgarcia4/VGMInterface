package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.CanalProvRubro;
import com.vgmsistemas.vgminterface.entity.Cliente;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Products;
import com.vgmsistemas.vgminterface.entity.unilever.Respuesta;
import com.vgmsistemas.vgminterface.entity.unilever.RespuestaDetalle;
import com.vgmsistemas.vgminterface.repository.CanalProvRubroRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.unilever.CuentaClienteRepo;
import com.vgmsistemas.vgminterface.repository.unilever.RespuestaRepo;
import com.vgmsistemas.vgminterface.service.ClienteService;
import com.vgmsistemas.vgminterface.servicesrest.CuentaClienteWs;


@Service
public class RespuestaService {

	@Autowired
	RespuestaRepo respuestaRepo;
	
	
	//private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	public Respuesta crear(Respuesta respuesta, String tabla) throws Exception {
		respuesta.setTabla(tabla);	
		
		for (RespuestaDetalle det:respuesta.getProducts()) {
			det.setRespuesta(respuesta);
		}
		// Grabo el cliente y devuelvo el resultado
			/*try {*/
				
				return respuestaRepo.save(respuesta);
			/*} catch (Exception e) {
				return null;
			}*/
		
	}
	
		
	public Optional<Respuesta> leerPorId(Integer id) throws Exception {
		 Optional<Respuesta> respuesta = respuestaRepo.findById(id);
		 
	     return respuesta;
	}
	
		
	public List<Respuesta> leerTodos() throws Exception {
		List<Respuesta> respuesta= respuestaRepo.findAll();
		
		return respuesta;
	}
	
}