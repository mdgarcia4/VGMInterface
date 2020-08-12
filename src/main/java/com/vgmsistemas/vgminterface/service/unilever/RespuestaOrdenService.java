package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.unilever.Order;
import com.vgmsistemas.vgminterface.entity.unilever.RespuestaOrden;
import com.vgmsistemas.vgminterface.entity.unilever.RespuestaOrdenDetalle;
import com.vgmsistemas.vgminterface.repository.unilever.OrderRepo;
import com.vgmsistemas.vgminterface.repository.unilever.RespuestaOrdenRepo;

@Service
public class RespuestaOrdenService {

	@Autowired
	RespuestaOrdenRepo respuestaOrdenRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	//private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	public RespuestaOrden crear(RespuestaOrden respuestaOrden, String tabla) throws Exception {
		respuestaOrden.setTabla(tabla);	
		
		for (RespuestaOrdenDetalle det:respuestaOrden.getOrders()) {
			det.setRespuesta(respuestaOrden);
		}
		// Grabo el cliente y devuelvo el resultado
			/*try {*/
				
				return respuestaOrdenRepo.save(respuestaOrden);
			/*} catch (Exception e) {
				return null;
			}*/
		
	}
	
		
	public Optional<RespuestaOrden> leerPorId(Integer id) throws Exception {
		 Optional<RespuestaOrden> respuesta = respuestaOrdenRepo.findById(id);
		 
	     return respuesta;
	}
	
		
	public List<RespuestaOrden> leerTodos() throws Exception {
		List<RespuestaOrden> respuesta= respuestaOrdenRepo.findAll();
		
		return respuesta;
	}


	public void actualizarEnvio(RespuestaOrden respuestaOrden, String tabla) {
		Optional<Order> order ;
		
		for (RespuestaOrdenDetalle det:respuestaOrden.getOrders()) {
			order = orderRepo.findByOrder_id("DEFINITIVA", det.getOrder_id());
			order.get().setSnEstadoEnviado("S");
			orderRepo.save(order.get());
		}
	
	}
}