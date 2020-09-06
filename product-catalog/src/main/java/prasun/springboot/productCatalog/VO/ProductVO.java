package prasun.springboot.productCatalog.VO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.productCatalog.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private int id;
	@NotBlank
	@NotNull
	private String name;
	@NotNull
	private Double price;
	@NotBlank
	@NotNull
	private String description;

	private Integer quantity;

	public ProductVO(Product product) {
		if (null != product) {
			this.id = product.get_id();
			this.name = product.getName();
			this.price = product.getPrice();
			this.description = product.getDescription();
			this.quantity = product.getQuantity();
		}
	}

	@JsonIgnore
	public Product getFromVO() {
		Product product = new Product();
		product.set_id(this.getId());
		product.setDescription(this.getDescription());
		product.setName(this.getName());
		product.setPrice(this.getPrice());
		product.setQuantity(quantity == null ? 100 : quantity);
		return product;
	}
}
