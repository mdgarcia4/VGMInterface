package com.vgmsistemas.vgminterface.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Proveedor;

@Repository
public interface ProveedorRepo extends JpaRepository<Proveedor, Long>  {
    public List<Proveedor> findBySnWeb(String snWeb);
 
}