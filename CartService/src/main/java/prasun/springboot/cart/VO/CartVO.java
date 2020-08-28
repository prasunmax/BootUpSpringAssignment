package prasun.springboot.cart.VO;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.cart.entity.Cart;
import prasun.springboot.cart.entity.Item;

@Data
@NoArgsConstructor
public class CartVO {

	String name;
	private List<Item> items;
	private Double totalAmount = 0.0;

	public CartVO(Cart cart) {
		if (null != cart) {
			this.name = cart.getId();
			this.items = cart.getItems();
		}
		this.totalAmount = 0.0;
		this.items.forEach(item -> totalAmount += item.getPrice());
	}
}
