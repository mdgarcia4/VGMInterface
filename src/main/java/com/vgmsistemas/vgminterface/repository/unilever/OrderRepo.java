package com.vgmsistemas.vgminterface.repository.unilever;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.unilever.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>  {
	@Query("SELECT o FROM Order o"
    		+ " WHERE o.tiOrden =?1"
    		+ "   and o.order_id =?2")
	public Optional<Order> findByOrder_id(String tiOrden,String order_id);

	@Query("SELECT o FROM Order o"
    		+ " WHERE o.snEstadoEnviado ='N'"
    		+ " and tiOrden =?1"
    		+ " and o.status not in ('102','106') ")
	public List<Order> findOrderByPendientes(String tiOrden);
	
	@Query("SELECT o FROM Order o"
    		+ " WHERE o.snEstadoEnviado ='N'"
    		+ " and tiOrden =?1"
    		+ " and o.status in ('102','106') ")
	public List<Order> findOrderByPendientesResumido(String tiOrden);
	
	@Query("SELECT o FROM Order o, CuentaCliente c"
    		+ " WHERE c.store_id_ERP = o.store_id_ERP"
    		+ " and o.snEstadoEnviado ='N'"
    		+ " and c.idSucursal =?1"
    		+ " and o.tiOrden =?2"
    		+ " and o.status not in ('102','106') ")
	public List<Order> findOrderBySucursalPendientes(long sucursal, String tiOrden);
	
	@Query("SELECT o FROM Order o, CuentaCliente c"
    		+ " WHERE c.store_id_ERP = o.store_id_ERP"
    		+ " and o.snEstadoEnviado ='N'"
    		+ " and c.idSucursal =?1"
    		+ " and o.tiOrden =?2"
    		+ " and o.status in ('102','106') ")
	public List<Order> findOrderBySucursalPendientesResumido(long sucursal, String tiOrden);
}