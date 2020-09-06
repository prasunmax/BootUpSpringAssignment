package prasun.springboot.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasun.springboot.order.VO.OrderInputVO;
import prasun.springboot.order.VO.OrderVO;
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
	public ResponseEntity<?> save(@RequestBody OrderInputVO order) {
		OrderVO orderVo = orderService.save(order);
		if (orderVo.getId() == 0) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.ok(orderService.save(order));
	}

}
