package prasun.springboot.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OrderColumn;
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
	@Embedded
	@OrderColumn( name = "product_id" )	 
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	@Embedded
	Address address;
}
