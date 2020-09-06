package prasun.springboot.order.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	Integer id;
	String name;
	Integer quantity;
	Double price;
	String description;
}
