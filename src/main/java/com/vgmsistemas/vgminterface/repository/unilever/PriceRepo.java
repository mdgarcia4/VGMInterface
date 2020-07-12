package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.PkPrice;
import com.vgmsistemas.vgminterface.entity.unilever.Price;

@Repository
public interface PriceRepo extends JpaRepository<Price, PkPrice>  {
	@Query("SELECT p FROM Price p"
    		+ " WHERE p.id.idArticulos =?1")
	public List<Price> findPrecioByIdArticulo(Long idArticulos);
}