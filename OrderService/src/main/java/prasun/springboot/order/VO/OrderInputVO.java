package prasun.springboot.order.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import prasun.springboot.order.entity.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputVO {
	Address address;
	String name;
}
