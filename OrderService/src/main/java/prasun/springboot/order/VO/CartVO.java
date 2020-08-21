package prasun.springboot.order.VO;

import java.util.List;


import lombok.Data;

@Data
public class CartVO {

	String name;
	private List<CartItem> items;
	
	
}
