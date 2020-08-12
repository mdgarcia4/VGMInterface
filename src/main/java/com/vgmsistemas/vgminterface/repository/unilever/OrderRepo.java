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
    		+ "   and tiOrden =?1")
	public List<Order> findOrderByPendientes(String tiOrden);
}