package prasun.springboot.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.order.VO.CartVO;
import prasun.springboot.order.VO.OrderInputVO;
import prasun.springboot.order.VO.OrderListVO;
import prasun.springboot.order.VO.OrderVO;
import prasun.springboot.order.entity.Order;
import prasun.springboot.order.repository.OrderRepository;
import prasun.springboot.order.repository.OrderSearchRepository;

@Service
@Slf4j
public class OrderService {

	private OrderRepository saveRepo;
	private OrderSearchRepository searchRepo;
	private SenderService sender;
	private CartServiceProxy cartServiceProxy;

	@Autowired
	public OrderService(OrderRepository saveRepo, OrderSearchRepository searchRepo, SenderService sender,
			CartServiceProxy cartServiceProxy) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.sender = sender;
		this.cartServiceProxy = cartServiceProxy;
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

	public OrderVO save(OrderInputVO order) {
		// Payment service Call to be implemented
		CartVO cartOrder = cartServiceProxy.getCartDetails(order.getName());
		log.info("Got the following from Cart:" + cartOrder);
		if (null == cartOrder || null == cartOrder.getName()) {
			return new OrderVO();
		}
		OrderVO ordervo = new OrderVO(cartOrder);
		ordervo.setAddress(order.getAddress());
		log.info("Order before saving" + ordervo);
		ordervo = new OrderVO(saveRepo.save(ordervo.getOrder()));
		// Order saved now update Product
		ordervo.getOrderItems().forEach(item -> {
			Map<String, Integer> hashMap = new HashMap<String, Integer>();
			hashMap.put("product_id", item.getProduct_id());
			hashMap.put("quantity", item.getQuantity());
			sender.sendToProduct(hashMap);
		});
		//Remove the user details from the cart, this can be made more dynamic
		Map<String, String> cartMap = new HashMap<String, String>();
		cartMap.put("user", order.getName());
		sender.sendToCart(cartMap);
		return ordervo;
	}

}
