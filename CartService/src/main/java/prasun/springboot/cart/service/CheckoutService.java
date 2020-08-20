package prasun.springboot.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.cart.entity.Cart;
import prasun.springboot.cart.repository.CheckoutRespository;

@Service
@Slf4j
public class CheckoutService {
	
	private CheckoutRespository checkoutRespository;
	
	
	@Autowired
	public CheckoutService(CheckoutRespository checkoutRespository) {
		super();
		this.checkoutRespository = checkoutRespository;
	}

	public Cart getdetails(String usrName) {
		log.info("Came for checkout details");
		return checkoutRespository.findById(usrName).get();
	}

	public Cart save(Cart checkout) {
		log.info("Came for checkout details");
		return checkoutRespository.save(checkout);
	}

}
