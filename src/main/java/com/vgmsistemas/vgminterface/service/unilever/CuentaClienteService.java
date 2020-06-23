package com.vgmsistemas.vgminterface.service.unilever;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgminterface.entity.Cliente;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.repository.CanalProvRubroRepo;
import com.vgmsistemas.vgminterface.repository.unilever.CuentaClienteRepo;
import com.vgmsistemas.vgminterface.restController.unilever.CuentaClienteRestController;
import com.vgmsistemas.vgminterface.service.ClienteService;

@Service
public class CuentaClienteService {

	@Autowired
	CuentaClienteRepo cuentaClienteRepo;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	CanalProvRubroRepo canalProvRubroRepo;

	//private static Logger LOG = LoggerFactory.getLogger(CuentaClienteRestController.class)	;
	
	public CuentaCliente tratarCuenta(CuentaCliente ctacliente) throws Exception {
		
		CuentaCliente cuentaCliente;
		// Verifico si store_id tiene dato.
		if (ctacliente.getStore_id_ERP()== null || ctacliente.getStore_id_ERP().equals("") ) {
			// Cliente nuevo y
			cuentaCliente = crear(ctacliente);
			return cuentaCliente;
		}
		else // El cliente ya existe porque viene Store_id
		{
			// Cliente nuevo y
			cuentaCliente = actualizar(ctacliente);
			return cuentaCliente;
		}
		
	}
	
		
	public CuentaCliente crear(CuentaCliente ctacliente) throws Exception {
		// Primero busco el cliente si existe para hacer match y asigno los campos
		Optional<Cliente> cliente = clienteService.findCliente(ctacliente);
		
		ctacliente.setSn_enviado('N');
		
		if (cliente.isPresent()) {
			
			ctacliente.setStore_id_ERP(cliente.get().getId().toString());
			ctacliente.setIdSucursal(cliente.get().getId().getIdSucursal());
			ctacliente.setIdCliente(cliente.get().getId().getIdCliente());
			ctacliente.setIdComercio(cliente.get().getId().getIdComercio());
			ctacliente.setStore_Status(1);
			ctacliente.setSn_enviado('S');
			
		    // Trato el rubro
			/*Integer rubro = cliente.get().getIdRubroCliente();
			Optional<CanalProvRubro> canal = canalProvRubroRepo.findCanalByProveedorAndRubro( 68, rubro);
			if (canal.isPresent()) {
				idCanalProv = canal.get().getId().getIdCanalProv();
			}*/
			
		}
		// Grabo el cliente y devuelvo el resultado
		ctacliente = cuentaClienteRepo.save(ctacliente);
		
		return ctacliente;
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
			cuentaCliente = cuentaClienteRepo.save(cuentaCliente);
			return cuentaCliente;
		} catch (Exception e) {
			
			return null;
		}
		
	}
	
	public Optional<CuentaCliente> leerPorId(Integer id) throws Exception {
		 Optional<CuentaCliente> cuentaCliente = cuentaClienteRepo.findById(id);
		 
	     return cuentaCliente;
	}
	public List<CuentaCliente> leerTodos() throws Exception {
		List<CuentaCliente> clientes = cuentaClienteRepo.findAll();
		
		return clientes;
	}
	
}