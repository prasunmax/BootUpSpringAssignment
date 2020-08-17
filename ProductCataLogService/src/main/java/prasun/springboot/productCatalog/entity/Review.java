package prasun.springboot.productCatalog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review extends GenericEntity{
	private int stars;
	private String author;
	private String body;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="product_id")
	private Product product;
}
