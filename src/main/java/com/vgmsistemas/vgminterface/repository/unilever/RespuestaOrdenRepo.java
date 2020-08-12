package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.RespuestaOrden;

@Repository
public interface RespuestaOrdenRepo extends JpaRepository<RespuestaOrden, Integer>  {
	public Optional<RespuestaOrden> findById(Integer id);
}