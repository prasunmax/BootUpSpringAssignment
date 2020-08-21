package prasun.springboot.cart.VO;

import java.util.List;

import lombok.Data;
import prasun.springboot.cart.entity.Cart;
import prasun.springboot.cart.entity.Item;

@Data
public class CartVO {

	String name;
	private List<Item> items;
	
	public CartVO(Cart cart) {
		this.name = cart.getId();
		this.items.addAll(cart.getItems());
	}
}
