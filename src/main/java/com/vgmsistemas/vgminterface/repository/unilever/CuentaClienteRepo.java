package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.List;
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
	
	@Query("SELECT c FROM CuentaCliente c"
    		+ " WHERE c.cuit_dni_id = ?1"
    		+ "   and c.street = ?2"
    		+ "   and c.number = ?3"
    		+ "   and c.postal_code = ?4"
    		+ "   and c.Neighborhood = ?5"
    		+ "   and c.District = ?6"
    		+ "   and c.Province = ?7")
	public Optional<CuentaCliente> findCuenta(String cuit_dni_id, String street, String number, String postal_code, String Neighborhood, String District, String Province);
	
	@Query("SELECT c FROM CuentaCliente c"
    		+ " WHERE c.sn_enviado = 'N'"
    		+ "   and c.Store_Status in ('0','1')"
    		)
	public List<CuentaCliente> findByPendientes();
}