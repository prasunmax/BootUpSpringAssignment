package prasun.springboot.cart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import prasun.springboot.cart.entity.Cart;
@Repository
public interface CartRespository extends CrudRepository<Cart, String>{

	

}
