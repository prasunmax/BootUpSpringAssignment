package prasun.springboot.productCatalog.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends GenericEntity{

	private String name;
	private String description;
	@Transient
	private int quantity;
	@Transient
	private double price;
	

}
