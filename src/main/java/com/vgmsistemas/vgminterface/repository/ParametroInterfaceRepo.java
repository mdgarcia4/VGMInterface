package com.vgmsistemas.vgminterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Empresa;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;

@Repository
public interface ParametroInterfaceRepo extends JpaRepository<ParametroInterface, Long>  {
	public Optional<ParametroInterface> findByIdEmpresa(Long idEmpresa);
	public Optional<ParametroInterface> findByIdEmpresaAndIdSucursal(Long idEmpresa, Long idSucursal);
}