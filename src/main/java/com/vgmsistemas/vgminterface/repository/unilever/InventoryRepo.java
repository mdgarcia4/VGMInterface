package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.Inventory;
import com.vgmsistemas.vgminterface.entity.unilever.PkInventory;


@Repository
public interface InventoryRepo extends JpaRepository<Inventory, PkInventory>  {
	@Query("SELECT i FROM Inventory i, Product p"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND p.idProveedor = ?2"
    		+ "   AND p.snWeb = ?3")
	public List<Inventory> findStock(Long idDeposito, Long idProveedor,String snWeb);
	
	@Query("SELECT i FROM Inventory i, Product p, Proveedor pr"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND p.idProveedor = pr.idProveedor"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND pr.snIntWeb = ?2"
    		+ "   AND p.snWeb = ?3")
	public List<Inventory> findStock(Long idDeposito, String snIntWeb,String snWeb);
	
	@Query("SELECT i FROM Inventory i, Product p"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND p.idProveedor = ?2"
    		+ "   AND p.snWeb = ?3"
    		+ "   and p.feIngreso = ?4 ")
	public List<Inventory> findStock(Long idDeposito, Long idProveedor,String snWeb, Date feIngreso);
	
	@Query("SELECT i FROM Inventory i, Product p, Proveedor pr"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND p.idProveedor =pr.idProveedor"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND pr.snIntWeb = ?2"
    		+ "   AND p.snWeb = ?3"
    		+ "   and p.feIngreso = ?4 ")
	public List<Inventory> findStock(Long idDeposito, String snIntWeb,String snWeb, Date feIngreso);
	
	@Query("SELECT i FROM Inventory i, Product p"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND p.EAN = ?2")
	public List<Inventory> findStockByEAN(Long idDeposito, String ean);
	
	@Query("SELECT i FROM Inventory i, Product p, ProductSelect s"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND p.EAN = s.EAN"
    		+ "   AND i.id.idDeposito = ?1")
	public List<Inventory> findStockByEAN(Long idDeposito);

	@Query("SELECT i FROM Inventory i, Product p"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND p.idProveedor = ?2"
    		+ "   AND p.snWeb = ?3")
	public Page<Inventory> findStockByPagina(Long idDeposito,Long idProveedor, String string, Pageable pagina);
	
	@Query("SELECT i FROM Inventory i, Product p, Proveedor pr"
    		+ " WHERE i.id.idArticulos = p.id"
    		+ "   AND p.idProveedor = pr.idProveedor"
    		+ "   AND i.id.idDeposito = ?1"
    		+ "   AND pr.snIntWeb = ?2"
    		+ "   AND p.snWeb = ?3")
	public Page<Inventory> findStockByPagina(Long idDeposito,String snIntWeb, String string, Pageable pagina);
}