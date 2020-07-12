package com.vgmsistemas.vgminterface.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.CanalProvRubro;
import com.vgmsistemas.vgminterface.entity.PkCanalProvRubro;

@Repository
public interface CanalProvRubroRepo extends JpaRepository<CanalProvRubro, PkCanalProvRubro>  {
    
	@Query("SELECT cpr FROM CanalProvRubro cpr, CanalProveedor cp "
    		+ " WHERE cpr.id.idProveedor = cp.id.idProveedor"
    		+ "   and cpr.id.idCanalProv = cp.id.idCanalProv"
    		+ "   and cpr.id.idProveedor = ?1"
    		+ "   and cp.deCanalProv =?2")
	public List<CanalProvRubro> findCanalByProveedorAndCanal(Long idProveedor, String deCanalProv);
	
	@Query("SELECT cpr FROM CanalProvRubro cpr, CanalProveedor cp "
    		+ " WHERE cpr.id.idProveedor = cp.id.idProveedor"
    		+ "   and cpr.id.idCanalProv = cp.id.idCanalProv"
    		+ "   and cpr.id.idProveedor = ?1"
    		+ "   and cpr.id.idRubro =?2")
	public Optional<CanalProvRubro> findCanalByProveedorAndRubro(Long idProveedor, Long idRubro);
 
}