package prasun.springboot.cart.entity;

import lombok.Data;

@Data
public class Item {

	Integer id;
	String name;
	Integer quantity;
	Double price;
	String description;
}
