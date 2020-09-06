package prasun.springboot.price.VO;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.price.entity.Price;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceVO {
	private int id;
	@NotNull
	private Integer product_id;
	@NotNull
	private Double price;
	@NotNull
	private Integer quantity;

	public PriceVO(Price price) {
		this.id = price.get_id();
		this.product_id = price.getProduct_id();
		this.price = price.getPrice();
		this.quantity = price.getQuantity();

	}
	@JsonIgnore
	public Price getFromVO() {
		Price price = new Price();
		price.set_id(this.getId());
		price.setQuantity(this.getQuantity());
		price.setProduct_id(this.getProduct_id());
		price.setPrice(this.getPrice());
		return price;
	}
}
