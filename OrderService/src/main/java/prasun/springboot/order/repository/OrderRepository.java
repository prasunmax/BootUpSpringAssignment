package prasun.springboot.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prasun.springboot.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}
