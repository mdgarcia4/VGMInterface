package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.unilever.CuentaCliente;

@Repository
public interface CuentaClienteRepo extends JpaRepository<CuentaCliente, Integer>  {
	@Query("SELECT c FROM CuentaCliente c"
    		+ " WHERE c.store_id_ERP = ?1")
	public CuentaCliente findCuentaByStore_id_ERP(String store_id_ERP);
	
	public Optional<CuentaCliente> findById(Integer id);
}