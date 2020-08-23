package prasun.springboot.order.VO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.order.entity.Address;
import prasun.springboot.order.entity.Order;
import prasun.springboot.order.entity.OrderItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
	int id;
	Double totalAmount;
	String name;

	List<OrderItem> orderItems;
	
	Address address;
	
	public OrderVO(Order order) {
		this.id = order.get_id();
		this.totalAmount = order.getTotalAmount();
		this.name = order.getName();
		this.address  = order.getAddress();
		this.orderItems=order.getOrderItems();
	}

	public OrderVO(CartVO cartOrder) {
		this.name = cartOrder.getName();
		totalAmount = copyCartItems( cartOrder.getItems());
	}
	
	@JsonIgnore
	private Double copyCartItems(List<CartItem> items) {
		orderItems = new ArrayList<>();
		var totalPrice = new Object() {
			Double price = 0.0;
		};
		items.forEach(item -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct_id(item.getId());
			orderItem.setQuantity(item.getQuantity());
			totalPrice.price += item.getPrice();
			orderItems.add(orderItem);
		});
		return totalPrice.price;		
	}
	
	@JsonIgnore
	public Order getOrder() {
		return new Order(this.totalAmount,this.name,this.orderItems, this.address);
	}
	
}
