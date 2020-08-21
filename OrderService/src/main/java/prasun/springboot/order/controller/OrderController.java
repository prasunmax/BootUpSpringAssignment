package prasun.springboot.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasun.springboot.order.entity.Order;
import prasun.springboot.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Order order){
		return ResponseEntity.ok(orderService.save(order));
	}

}
