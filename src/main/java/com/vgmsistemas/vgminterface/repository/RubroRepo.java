package com.vgmsistemas.vgminterface.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Negocio;
import com.vgmsistemas.vgminterface.entity.Rubro;


@Repository
public interface RubroRepo extends JpaRepository<Rubro, Long>  {
    public List<Rubro> findByNegocio(Negocio negocio);
    public List<Rubro> findBySnWeb(String snWeb);
}