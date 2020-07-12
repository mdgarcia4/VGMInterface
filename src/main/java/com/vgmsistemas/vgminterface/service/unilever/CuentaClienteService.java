package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.CanalProvRubro;
import com.vgmsistemas.vgminterface.entity.Cliente;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.repository.CanalProvRubroRepo;
import com.vgmsistemas.vgminterface.repository.ParametroInterfaceRepo;
import com.vgmsistemas.vgminterface.repository.unilever.CuentaClienteRepo;
import com.vgmsistemas.vgminterface.service.ClienteService;
import com.vgmsistemas.vgminterface.servicesrest.CuentaClienteWs;



@Service
public class CuentaClienteService {

	@Autowired
	CuentaClienteRepo cuentaClienteRepo;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	CanalProvRubroRepo canalProvRubroRepo;
	
	@Autowired
	ParametroInterfaceRepo parametroInterfaceRepo;
	
	private static Logger LOG =  LoggerFactory.getLogger(CuentaClienteService.class)	;
	
	public CuentaCliente tratarCuenta(CuentaCliente ctacliente) throws Exception {
		
		CuentaCliente cuentaCliente;
		// Verifico si store_id tiene dato.
		if (ctacliente.getStore_id_ERP()== null || ctacliente.getStore_id_ERP().equals("") ) {
			// Verifico si el registro ya ingresó pero esta con Store_id_ERP vacio
			Optional<CuentaCliente> cl;
			cl =cuentaClienteRepo.findCuenta(ctacliente.getCuit_dni_id(), ctacliente.getStreet(), ctacliente.getNumber(), ctacliente.getPostal_code(), ctacliente.getNeighborhood(), ctacliente.getDistrict(), ctacliente.getProvince()); 
			if (!cl.isPresent()) {
				// Cliente nuevo y
				cuentaCliente = crear(ctacliente);
				return cuentaCliente;
			} else {
				LOG.info("CuentaCliente tratarCuenta(). El Retailer con DNI " + ctacliente.getCuit_dni_id() + " ya existe. No se crea un nuevo registro, se devuelve el actual");	
				return cl.get();
			}
			
		}
		else // El cliente ya existe porque viene Store_id
		{
			// Cliente nuevo y
			LOG.info("CuentaCliente tratarCuenta(). El Retailer con store_id_ERP nro " + ctacliente.getStore_id_ERP() + " se va a actualizar. ");
			cuentaCliente = actualizar(ctacliente);
			LOG.info("CuentaCliente tratarCuenta(). Actualización OK. Se actualizó el store_id_ERP nro " + ctacliente.getStore_id_ERP() );
			return cuentaCliente;
		}
		
	}
	
		
	public CuentaCliente crear(CuentaCliente ctacliente) throws Exception {
		String vacio= "";
		// Primero busco el cliente si existe para hacer match y asigno los campos
		Optional<Cliente> cliente = clienteService.findCliente(ctacliente);
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		
		ctacliente.setSn_enviado('N');
		ctacliente.setStore_Status(vacio);
		ctacliente.setRejection_reason(vacio);
		if (cliente.isPresent()) {
			
			ctacliente.setStore_id_ERP(cliente.get().getId().toString());
			ctacliente.setIdSucursal(cliente.get().getId().getIdSucursal());
			ctacliente.setIdCliente(cliente.get().getId().getIdCliente());
			ctacliente.setIdComercio(cliente.get().getId().getIdComercio());
			ctacliente.setErp_seller(cliente.get().getIdVendedor());
			ctacliente.setStore_Status("1");
						
		    // Trato el rubro
			Integer rubro = cliente.get().getIdRubroCliente();
			LOG.info("CuentaCliente crear(). Antes de buscar el canal del proveedor con el rubro " + rubro );
			LOG.info("Proveedor N°: " + param.get().getIdProveedor());
			Optional<CanalProvRubro> canal = canalProvRubroRepo.findCanalByProveedorAndRubro( (long) param.get().getIdProveedor(),(long) rubro);
			if (canal.isPresent()) {
				ctacliente.setCustomer_type(String.valueOf(canal.get().getId().getIdCanalProv())); ;
			}
			
		}
		// Grabo el cliente y devuelvo el resultado
		try {
			LOG.info("CuentaCliente.crear(). Trato de crear el Retailer. " + ctacliente.getCuit_dni_id());
			ctacliente = cuentaClienteRepo.save(ctacliente);
			LOG.info("CuentaCliente.crear(). Creó OK el Retailer. " + ctacliente.getCuit_dni_id());
			return ctacliente;
		} catch (Exception e) {
			LOG.error("CuentaCliente crear(). Al tratar de crear el Retailer. " + e.getMessage());
			throw e;
		}
		
	}
	
	public CuentaCliente actualizar(CuentaCliente ctacliente) throws Exception {
		CuentaCliente cuentaCliente;
		
		cuentaCliente = cuentaClienteRepo.findCuentaByStore_id_ERP(ctacliente.getStore_id_ERP());
		
		cuentaCliente.setDistributor_code(ctacliente.getDistributor_code());
		cuentaCliente.setProperty_name(ctacliente.getProperty_name());
		cuentaCliente.setCuit_dni_id(ctacliente.getCuit_dni_id());
		cuentaCliente.setDocument_type_id(ctacliente.getDocument_type_id());
		cuentaCliente.setIngresos_brutos(ctacliente.getIngresos_brutos());
		cuentaCliente.setCustomer_type(ctacliente.getCustomer_type());
		cuentaCliente.setStreet(ctacliente.getStreet());
		cuentaCliente.setNumber(ctacliente.getNumber());
		cuentaCliente.setNeighborhood(ctacliente.getNeighborhood());
		cuentaCliente.setDistrict(ctacliente.getDistrict());
		cuentaCliente.setProvince(ctacliente.getProvince());
		cuentaCliente.setCountry(ctacliente.getCountry());
		cuentaCliente.setPostal_code(ctacliente.getPostal_code());
		cuentaCliente.setPhone_number(ctacliente.getPhone_number());
		cuentaCliente.setMobile_number(ctacliente.getMobile_number());
		cuentaCliente.setEmail(ctacliente.getEmail());
		cuentaCliente.setFirstname(ctacliente.getFirstname());
		cuentaCliente.setSurname(ctacliente.getSurname());
		
		
		// Grabo las modificaciones cliente y devuelvo el resultado
		try {
			LOG.info("CuentaCliente actualizar(). Trato de actualizar el Retailer. " + ctacliente.getCuit_dni_id());
			cuentaCliente = cuentaClienteRepo.save(cuentaCliente);
			LOG.info("CuentaCliente actualizar(). Aactualiza ok el Retailer. " + ctacliente.getCuit_dni_id());
			return cuentaCliente;
		} catch (Exception e) {
			LOG.error("CuentaCliente actualizar(). Al tratar de actualizar el Retailer. " + e.getMessage());
			throw e;
		}
		
	}
	
	public CuentaCliente actualizarEnviado(CuentaCliente ctacliente) throws Exception {
		CuentaCliente cuentaCliente;
		
		cuentaCliente = cuentaClienteRepo.findCuentaByStore_id_ERP(ctacliente.getStore_id_ERP());
		
		cuentaCliente.setSn_enviado('S');
		
		// Grabo las modificaciones cliente y devuelvo el resultado
		try {
			LOG.info("CuentaCliente actualizarEnviado(). Trato de actualizar el Estado a Enviado del Retailer. " + ctacliente.getCuit_dni_id());
			cuentaCliente = cuentaClienteRepo.save(cuentaCliente);
			LOG.info("CuentaCliente actualizarEnviado(). Actualización del Estado OK del Retailer. " + ctacliente.getCuit_dni_id());
			return cuentaCliente;
		} catch (Exception e) {
			LOG.error("CuentaCliente actualizarEnviado(). Al tratar de actualizar el Estado del Retailer. " + e.getMessage());
			throw e;
		}
		
	}
	
	public Optional<CuentaCliente> leerPorId(Integer id) throws Exception {
		 Optional<CuentaCliente> cuentaCliente = cuentaClienteRepo.findById(id);
		 
	     return cuentaCliente;
	}
	
	public Integer enviar(Integer id) throws Exception {
		CuentaClienteWs cuentaClienteWs = new CuentaClienteWs();
				
		// Optengo los parametros de la interface
		Optional<ParametroInterface> param = parametroInterfaceRepo.findById((long) 1);
		LOG.error("CuentaClienteService enviar(). Obtengo parámetros");
		LOG.error("enviar(). Url: " + param.get().getDeUrlCuentaClienteEstado());
		LOG.error("enviar(). Client Id: " + param.get().getClient_id());
		LOG.error("enviar(). Client secret: " + param.get().getClient_secret());
		
		// Recupero la cuenta cliente
		Optional<CuentaCliente> cuentaCliente = cuentaClienteRepo.findById(id);
		
		if (!cuentaCliente.isPresent()) {
			LOG.error("CuentaClienteService enviar(). El Retailer Nro " + id + " no existe");
			throw new Exception("CuentaClienteService enviar(). El Retailer Nro " + id + " no existe");
		};
		
		// Envio al Web Service
		LOG.error("enviar(). Llamo metodo callWebService: CUIT " + cuentaCliente.get().getCuit_dni_id());
	    return cuentaClienteWs.callWebService(cuentaCliente, param);
	}
	
	public Integer enviar() throws Exception {
				
		// Recupero la cuenta cliente
		List<CuentaCliente> cuentasCliente = cuentaClienteRepo.findByPendientes();
		
		for (CuentaCliente cuenta : cuentasCliente) {
			enviar(cuenta.getId());
		}
		
		
	    return 1;
	}
	
	public List<CuentaCliente> leerTodos() throws Exception {
		List<CuentaCliente> clientes = cuentaClienteRepo.findAll();
		
		return clientes;
	}
	
}