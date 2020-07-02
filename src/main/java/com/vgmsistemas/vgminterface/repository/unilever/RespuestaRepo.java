package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.Respuesta;

@Repository
public interface RespuestaRepo extends JpaRepository<Respuesta, Integer>  {
	public Optional<Respuesta> findById(Integer id);
}