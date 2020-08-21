package prasun.springboot.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.Order.VO.OrderListVO;
import prasun.springboot.Order.VO.OrderVO;
import prasun.springboot.order.VO.CartVO;
import prasun.springboot.order.entity.Order;
import prasun.springboot.order.repository.OrderRepository;
import prasun.springboot.order.repository.OrderSearchRepository;

@Service
@Slf4j
public class OrderService {

	private OrderRepository saveRepo;
	private OrderSearchRepository searchRepo;
	@Value("${microservices.endpoints.endpoint.productCatalog.products}")
	private String templateVal;
	private SenderService sender;
	

	@Autowired
	public OrderService(OrderRepository saveRepo, OrderSearchRepository searchRepo,
			ProductServiceProxy productServiceProxy, SenderService sender) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.sender = sender;
	}

	public OrderListVO findAll() {
		log.info("Finding all Orders");
		List<OrderVO> lists = new ArrayList<OrderVO>();
		searchRepo.findAll().forEach(Order -> lists.add(new OrderVO(Order)));
		OrderListVO listVO = new OrderListVO(lists);
		return listVO;
	}

	public Order getOrderByOrderId(int OrderId) {
		log.info("Finding by Order id" + OrderId);
		return searchRepo.queryByOrderId(OrderId);
	}

	public OrderListVO queryByProductId(int productId) {
		log.info("Finding by Order id" + productId);
		List<Order> orders = searchRepo.queryByProductId(productId);
		List<OrderVO> orderLists = new ArrayList<OrderVO>();
		orders.forEach(order -> orderLists.add(new OrderVO(order)));
		OrderListVO listVo = new OrderListVO(orderLists);
		return listVo;
	}

	public OrderVO save(Order Order) {
		//Payment service Call to be implemented
		
		OrderVO Ordervo = new OrderVO(saveRepo.save(Order));
		//Order saved 
		Ordervo.getOrderItems().forEach(item -> {
			Map<String, Double> hashMap = new HashMap<String, Double>();
			hashMap.put("product_id", Double.valueOf(item.getProduct_id()));
			hashMap.put("quantity", Double.valueOf(item.getQuantity()));
			sender.send(hashMap);
		});
		return new OrderVO(saveRepo.save(Order));
	}

}
