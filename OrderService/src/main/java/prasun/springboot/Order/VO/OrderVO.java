package prasun.springboot.Order.VO;

import java.util.List;

import javax.persistence.ElementCollection;

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

	@ElementCollection
	List<OrderItem> orderItems;
	
	Address address;
	
	public OrderVO(Order order) {
		this.id = order.get_id();
		this.totalAmount = order.getTotalAmount();
		this.name = order.getName();
		this.address  = order.getAddress();
		this.orderItems.addAll(order.getOrderItems());
	}
	
	

}
