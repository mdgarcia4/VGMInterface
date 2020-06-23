package com.vgmsistemas.vgminterface.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.PkRolesUsuarios;
import com.vgmsistemas.vgminterface.entity.RolesUsuarios;


@Repository
public interface RolesUsuariosRepo extends JpaRepository<RolesUsuarios, PkRolesUsuarios>  {
    
}