package com.vgmsistemas.vgminterface.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Sucursal;


@Repository
public interface SucursalRepo extends JpaRepository<Sucursal, Long>  {
		
}