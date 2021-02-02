package com.vgmsistemas.vgminterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.Empresa;
import com.vgmsistemas.vgminterface.entity.ProveedorSucursal;


@Repository
public interface EmpresaRepo extends JpaRepository<Empresa, Long>  {
	public Optional<Empresa> findBySnActivada(String snActivada);
}