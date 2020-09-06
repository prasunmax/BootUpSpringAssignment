package prasun.springboot.cart.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private Integer id;
	private String name;
	private Double price;
	private String description;

	private Integer quantity;

}
