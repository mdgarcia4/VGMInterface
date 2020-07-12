package com.vgmsistemas.vgminterface.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgminterface.entity.Cliente;
import com.vgmsistemas.vgminterface.entity.PkCliente;
import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;
import com.vgmsistemas.vgminterface.repository.ClienteRepo;

@Service
public class ClienteService {

	@Autowired
	ClienteRepo clienteRepo;
	
	private static Logger LOG = LoggerFactory.getLogger(ClienteService.class)	;

	public Optional<Cliente> getAll(PkCliente id)  {
		 Optional<Cliente> cliente = clienteRepo.findById(id);
	     return cliente;
	}
	
	public Cliente getClienteByUsuario(String usuario){
		Cliente cliente = clienteRepo.findClienteByUsuario(usuario);
		return cliente;
	}
	
	public Optional<Cliente> findCliente(CuentaCliente ctaCliente) {
		
		String cuitDni = ctaCliente.getCuit_dni_id();
		String domicilio = ctaCliente.getStreet() + ' ' + ctaCliente.getNumber();
		Integer codigoPostal = Integer.parseInt(ctaCliente.getPostal_code().substring(1, 5));
		String provincia = ctaCliente.getProvince();
		Optional<Cliente> clie ;
		
		if (ctaCliente.getDocument_type_id().charAt(0) == '1') {
			// Busco primero por cuit y domicilio
			LOG.info(" findCliente() . Antes de llamar a findClienteByCuit" );
			LOG.info("CUIT/DNI: " + cuitDni);
			LOG.info("DOMICILIO: " + domicilio);
			LOG.info("Codigo Postal: " + codigoPostal);
			LOG.info("provincia: " + provincia);
			clie = clienteRepo.findClienteByCuit(cuitDni, domicilio, codigoPostal, provincia.toUpperCase());
		} else {
			// Busco por dni y domicilio
			LOG.info(" findCliente() . Antes de llamar a findClienteByCuit" );
			LOG.info("CUIT/DNI: " + cuitDni);
			LOG.info("DOMICILIO: " + domicilio);
			LOG.info("Codigo Postal: " + codigoPostal);
			LOG.info("provincia: " + provincia);
			clie = clienteRepo.findClienteByDni(cuitDni, domicilio, codigoPostal, provincia.toUpperCase());
		}
		return clie;   
	}
	
	
	

	public ClienteRepo getClienteRepo() {
		return clienteRepo;
	}

	public void setClienteRepo(ClienteRepo clienteRepo) {
		this.clienteRepo = clienteRepo;
	}
	
	
	
}