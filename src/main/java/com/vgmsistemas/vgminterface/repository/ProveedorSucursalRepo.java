package com.vgmsistemas.vgminterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.ProveedorSucursal;


@Repository
public interface ProveedorSucursalRepo extends JpaRepository<ProveedorSucursal, Long>  {
	public Optional<ProveedorSucursal> findByIdSucursalAndIdProveedor(long id, long idProveedor);
}