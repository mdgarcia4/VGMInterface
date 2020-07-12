package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>  {
	public List<Product> findByIdProveedor(Long idProveedor);
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2")
	public List<Product> findProdByIdProveedor(Long idProveedor, String snWeb);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2")
	public Page<Product> findProdByPagina(Long idProveedor, String snWeb, Pageable pageable);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feIngreso = ?3 ")
	public List<Product> findProdModByProveedor(Long idProveedor, String snWeb, Date feIngreso);
}