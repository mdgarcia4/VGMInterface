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
public interface ProductRepo extends JpaRepository<Product, Long>  {
	public List<Product> findByIdProveedor(Long idProveedor);
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2")
	public List<Product> findProdByIdProveedor(Long idProveedor, String snWeb);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2")
	public List<Product> findProdByProveedores(String snIntWeb, String snWeb);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2")
	public Page<Product> findProdByPagina(Long idProveedor, String snWeb, Pageable pageable);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb =?1"
    		+ "   and p.snWeb =?2")
	public Page<Product> findProdByPagina(String snIntWeb, String snWeb, Pageable pageable);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feIngreso = ?3 ")
	public List<Product> findProdModByProveedor(Long idProveedor, String snWeb, Date feIngreso);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feIngreso = ?3 ")
	public List<Product> findProdModByProveedor(String snIntWeb, String snWeb, Date feIngreso);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.id =?1")
	public List<Product> findProdById(Long id);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.EAN =?1")
	public List<Product> findProdByEAN(String ean);
	
	@Query("SELECT p FROM Product p, ProductSelect s"
    		+ " WHERE p.EAN = s.EAN")
	public List<Product> findProdByEAN();
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActualizacion = ?3 ")
	public List<Product> findProdModByProveedorFeActualiza(Long idProveedor, String snWeb, Date feActualizacion);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActualizacion = ?3 ")
	public List<Product> findProdModByProveedorFeActualiza(String snIntWeb, String snWeb, Date feActualizacion);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActPrecioInterfaz = ?3 ")
	public List<Product> findProdModByProveedorFeActPrecioInterfaz(Long idProveedor, String snWeb, Date feActPrecioInterfaz);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActPrecioInterfaz = ?3 ")
	public List<Product> findProdModByProveedorFeActPrecioInterfaz(String snIntWeb, String snWeb, Date feActPrecioInterfaz);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActualizacion = ?3 ")
	public Page<Product> findProdByActualizaPagina(Long idProveedor, String snWeb, Date feActualizacion, Pageable pageable);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActualizacion = ?3 ")
	public Page<Product> findProdByActualizaPagina(String snIntWeb, String snWeb, Date feActualizacion, Pageable pageable);
	
	@Query("SELECT p FROM Product p"
    		+ " WHERE p.idProveedor =?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActPrecioInterfaz = ?3 ")
	public Page<Product> findProdByActPrecioInterfazPagina(Long idProveedor, String snWeb, Date feActualizacion, Pageable pageable);
	
	@Query("SELECT p FROM Product p, Proveedor pr"
    		+ " WHERE p.idProveedor = pr.idProveedor"
    		+ "   and pr.snIntWeb = ?1"
    		+ "   and p.snWeb =?2"
    		+ "   and p.feActPrecioInterfaz = ?3 ")
	public Page<Product> findProdByActPrecioInterfazPagina(String snIntWeb, String snWeb, Date feActualizacion, Pageable pageable);
}