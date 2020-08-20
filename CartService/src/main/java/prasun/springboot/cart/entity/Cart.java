package prasun.springboot.cart.entity;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("cart")
@Data
public class Cart {
	
	private String id;    
	private List<item> items;

    @Data
    class item{
    	Integer id;
    	String name;
    	Integer quantity;
    	Double price;
    }
}
