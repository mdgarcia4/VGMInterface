package com.vgmsistemas.vgminterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Cliente;
import com.vgmsistemas.vgminterface.entity.PkCliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, PkCliente>  {
	
	@Query("SELECT c FROM Cliente c, Usuario u "
    		+ "WHERE c.id.idSucursal = u.idSucursal"
			+ "  and c.id.idCliente = u.idCliente"
			+ "  and c.id.idComercio = u.idComercio"
    		+ "  and u.usuario= ?1")
	public Cliente findClienteByUsuario(String usuario);
	
	public Optional<Cliente> findByCuitAndDomicilio(String cuit, String domicilio);
	
	public Optional<Cliente> findByIdNroDocAndDomicilio(String cuit, String domicilio);
	
	@Query("SELECT c FROM Cliente c "
    		+ "WHERE "
    		+ " cuit = ?1"
    		+ " and c.domicilio = ?2"
    		+ " and c.localidad.codigoPostal = ?3"
    		+ " and c.localidad.provincia.descripcion = ?4")
	public Optional<Cliente> findClienteByCuit(String cuit, String domicilio, Integer codigoPostal, String provincia);
	
	@Query("SELECT c FROM Cliente c "
    		+ "WHERE "
    		+ " idNroDoc = ?1"
    		+ " and c.domicilio = ?2"
    		+ " and c.localidad.codigoPostal = ?3"
    		+ " and c.localidad.provincia.descripcion = ?4")
	public Optional<Cliente> findClienteByDni(String dni, String domicilio, Integer codigoPostal, String provincia);
}