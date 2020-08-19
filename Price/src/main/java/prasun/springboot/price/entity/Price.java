package prasun.springboot.price.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import prasun.springboot.price.VO.ProductVO;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "price")
@Entity
public class Price extends GenericEntity{
	
	private double price;
	private int product_id;
	private int quantity;

	public Price(ProductVO product) {
		this.price = product.getPrice();
		this.product_id = product.getId();
		this.quantity = product.getQuantity();
	}
}
