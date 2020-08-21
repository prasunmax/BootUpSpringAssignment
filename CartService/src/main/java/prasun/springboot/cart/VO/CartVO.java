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
	
	public CartVO(Cart cart) {
		this.name = cart.getId();
		this.items.addAll(cart.getItems());
	}
}
