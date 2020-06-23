package com.vgmsistemas.vgminterface.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.CanalProveedor;
import com.vgmsistemas.vgminterface.entity.PkCanalProveedor;

@Repository
public interface CanalProveedorRepo extends JpaRepository<CanalProveedor, PkCanalProveedor>  {
    
	@Query("SELECT cp FROM CanalProveedor cp "
    		+ " WHERE cp.id.idProveedor = ?1"
    		+ "   and cp.id.idCanalProv =?2")
	public List<CanalProveedor> findCanalByProveedorAndRubro(Long proveedor, Long canal);
 
}