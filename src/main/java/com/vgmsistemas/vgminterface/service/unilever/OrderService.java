package com.vgmsistemas.vgminterface.service.unilever;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Empresa;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.Proveedor;
import com.vgmsistemas.vgminterface.entity.ProveedorSucursal;
import com.vgmsistemas.vgminterface.entity.Sucursal;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.entity.unilever.Order;
import com.vgmsistemas.vgminterface.entity.unilever.OrderItem;
import com.vgmsistemas.vgminterface.entity.unilever.OrderItemChanged;
import com.vgmsistemas.vgminterface.entity.unilever.Orders;
import com.vgmsistemas.vgminterface.repository.EmpresaRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorRepo;
import com.vgmsistemas.vgminterface.repository.ProveedorSucursalRepo;
import com.vgmsistemas.vgminterface.repository.SucursalRepo;
import com.vgmsistemas.vgminterface.repository.unilever.OrderRepo;
import com.vgmsistemas.vgminterface.servicesrest.OrderWs;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	ParametroInterfaceRepo parametroInterfaceRepo;
	
	@Autowired
	SucursalRepo sucursalRepo;
	
	@Autowired
	ProveedorRepo proveedorRepo;
	
	@Autowired
	EmpresaRepo  empresaRepo;
	
	@Autowired
	ProveedorSucursalRepo proveedorSucursalRepo;
	
	@Value("${sucursalDefault}")
	long idSucursal;
	
	private static Logger LOG =  LoggerFactory.getLogger(OrderService.class)	;
	
	
	// ---- Metodos para recibir las ordenes
	
	// Metodo que trata la lista de ordenes para tratar
	public Orders tratar(Orders orders, String tiOrden) throws Exception {
		List<Order> ords = orders.getOrders();
		
		// Creo orden Original
		for (Order order:ords) {
			order.setDistributor_code(orders.getDistributor_code());
			
			// Esto se hace porque tiene que llegar a la página en positivo para que se envie el correo al cliente
			if (order.getDiscount_amount()<0) {
				order.setDiscount_amount(order.getDiscount_amount() * -1);
			}
			order = tratarOrden(order,tiOrden);
			order.setStatus("1");
			
		}
		
	return orders;	
	}
	
	// Método para tratar una orden
	
public Order tratarOrden(Order ordenRecibida, String tiOrden) throws Exception {
		
		// Verifico si el registro ya ingresó 
		Optional<Order> orden;
		orden =orderRepo.findByOrder_id(tiOrden,ordenRecibida.getOrder_id()); 
		if (!orden.isPresent()) {
			// Cliente nuevo y
			
			ordenRecibida = crear(ordenRecibida, tiOrden);
			
			return ordenRecibida;
		} else {
			LOG.info("Order tratarOrden(). La Orden " + orden.get().getOrder_id() + " ya existe. No se crea un nuevo registro, se devuelve el actual");
			
			return ordenRecibida;
		}
	}
	
	
	// Metodo que crea UNA ORDEN
	public Order crear(Order order, String tiOrden) throws Exception {
		order.setTiOrden(tiOrden);
		order.setOrder_source("compreahora");
		order.setSnEstadoEnviado("N");
		order.setSnGenerado("N");
		order.setErp_order_id("");
		order.setUpdated_at(new Date());
		
		for (OrderItem item:order.getItems()) {
			item.setOrder(order);
			item.setCaArticulosFc(item.getQty_ordered());
			item.setCaArticulosNc((float) 0);
		}
		
		order.getPayment().setOrder1(order);
		order.getShipping().setOrder2(order);
		order.getShipping().getAddress().setShipping(order.getShipping());
		
		// Grabo el cliente y devuelvo el resultado
			/*try {*/
				
				return orderRepo.save(order);
			/*} catch (Exception e) {
				return null;
			}*/
		
	}
	
		
	
	
	
	
	// ---- METODOS PARA ENVIAR ORDENES ACTUALIZADAS 
	public Orders prepararOrden(ParametroInterface param, String tipo) throws Exception  {
		Long idProveedor = param.getIdProveedor();
		Map<String, String> items_changed;
		
		
		Optional<Empresa> empresa = empresaRepo.findBySnActivada("S");
		
		// Recupero el codigo de distribuidor
		Optional<Proveedor> prov = proveedorRepo.findById(idProveedor);
				
		// 1 sucursal
				// Optional<SucursalProveedor> provSuc = sucrusalProveedorRepo.findById(id Sucursal,idProveedor);
				// n sucursales
		// Recupero la cuenta cliente
		List<Order> ordenLista;
		if (empresa.get().getTiImplementacionInterfaz().equals("1")) {
			if (tipo =="COMPLETO") {
				ordenLista = orderRepo.findOrderBySucursalPendientes(idSucursal, "DEFINITIVA");
			} else {
				ordenLista = orderRepo.findOrderBySucursalPendientesResumido(idSucursal, "DEFINITIVA");
			}
		} else {
			if (tipo =="COMPLETO") {
				ordenLista = orderRepo.findOrderByPendientes("DEFINITIVA");
			} else {
				ordenLista = orderRepo.findOrderByPendientesResumido("DEFINITIVA");
			}
		}
		
		
		for (Order ord:ordenLista) {
			items_changed = new HashMap<String, String>();
			for (OrderItemChanged cambiados:ord.getItems_cambiados()) {
				if (ord.getStatus().equals(cambiados.getStatus())) {
					items_changed.put(cambiados.getEAN(), cambiados.getReason());
				}
			}
			ord.setItems_changed(items_changed);
		}
		
		// Creo el objeto y le asigno la lista
		Orders ordenes = new Orders(ordenLista);
		
		// Seteo el código de distribuidor
		//ordenes.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		if (empresa.get().getTiImplementacionInterfaz().equals("1")  ) {
			Optional<ProveedorSucursal> proveedorSucursal = proveedorSucursalRepo.findByIdSucursalAndIdProveedor(idSucursal, prov.get().getIdProveedor());
			if (proveedorSucursal.isPresent()) {
				ordenes.setDistributor_code(String.valueOf(proveedorSucursal.get().getIdClienteProv()));
			} else {
				ordenes.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
			}
			
		} else {
			ordenes.setDistributor_code(String.valueOf(prov.get().getIdClienteProv()));
		}
		
		return ordenes;
	}
	
	public Integer enviar() throws Exception {
		return enviar("COMPLETO");
	}
	
	public Integer enviar(String tipo) throws Exception {
		OrderWs orderWs = new OrderWs();
				
		// Sucursales
		Optional<Sucursal> suc = sucursalRepo.findById(idSucursal);
				
		// Optengo los parametros de la interface
		// Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		Optional<Empresa> empresa = empresaRepo.findBySnActivada("S");
		
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param ;
		
		// Optengo los parametros de la interface
		if (empresa.get().getTiImplementacionInterfaz().equals("1")   ) {
			param = parametroInterfaceRepo.findByIdEmpresaAndIdSucursal(empresa.get().getId(),idSucursal);
		} else { 
			// Optengo los parametros de la interface
			param = parametroInterfaceRepo.findByIdEmpresa(empresa.get().getId());
		}
		
		LOG.info("OrderService enviar(). Obtengo parámetros");
		LOG.info("enviar(). Url: " + param.get().getDeUrlEstadoVentas());
		LOG.info("enviar(). Client Id: " + param.get().getClient_id());
		LOG.info("enviar(). Client secret: " + param.get().getClient_secret());
		
		// Preparo la orden para enviar
		Orders ordenes = prepararOrden( param.get(), tipo);
		
		// Envio al Web Service
		LOG.info("enviar(). Llamo metodo callWebService con ordenes: " );
		return orderWs.callWebService(ordenes, param, tipo);
		
	    
	}
}