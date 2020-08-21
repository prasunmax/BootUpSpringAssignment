package prasun.springboot.cart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.cart.VO.CartVO;
import prasun.springboot.cart.VO.ProductVO;
import prasun.springboot.cart.entity.Cart;
import prasun.springboot.cart.entity.Item;
import prasun.springboot.cart.repository.CheckoutRespository;

@Service
@Slf4j
public class CartService {

	private CheckoutRespository checkoutRespository;
	private ProductServiceProxy productServiceProxy;

	@Autowired
	public CartService(CheckoutRespository checkoutRespository,
			prasun.springboot.cart.service.ProductServiceProxy productServiceProxy) {
		super();
		this.checkoutRespository = checkoutRespository;
		this.productServiceProxy = productServiceProxy;
	}

	public Cart getdetails(String usrName) {
		log.info("Came for checkout details for Name:" + usrName);
		return checkoutRespository.findById(usrName).get();
	}

	public CartVO save(CartVO cart) {
		Cart existingCart = checkCart(getdetails(cart.getName()));
		if (null == existingCart.getItems()) {
			existingCart.setItems(new ArrayList<Item>());
		}
		log.info("Came for checkout details");
		cart.getItems().forEach(item -> {
			var wrapper = new Object() {
				boolean found = false;
			};
			existingCart.getItems().forEach(item1 -> {
				if (item1.getId() == item.getId()) {
					// Add quantity for similar items
					item1.setQuantity(item.getQuantity() + item1.getQuantity());
					wrapper.found = true;
				}
			});
			//ProductVO productVO = productServiceProxy.getProduct(item.getId());
			// If the item is not existing
			if (!wrapper.found) {
				/*
				 * if (null != productVO) { item.setName(productVO.getName());
				 * item.setDescription(productVO.getDescription()); }
				 */
				existingCart.getItems().add(item);
			}
		});
		return new CartVO(checkoutRespository.save(existingCart));
	}

	private Cart checkCart(Cart cart) {
		return cart == null ? new Cart() : cart;
	}

}
