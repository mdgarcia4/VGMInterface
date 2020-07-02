package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>  {
	public List<Product> findByIdProveedor(Long idProveedor);
}