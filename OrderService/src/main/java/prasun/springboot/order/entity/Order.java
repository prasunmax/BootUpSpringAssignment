package prasun.springboot.order.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
@Entity
public class Order extends GenericEntity{
	Double totalAmount;
	String name;

	@ElementCollection
	List<OrderItem> orderItems;
	
	@Embedded
	Address address;
}
