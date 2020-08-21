package prasun.springboot.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import prasun.springboot.cart.VO.CartVO;
import prasun.springboot.cart.service.CartService;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {

	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CartVO cart, BindingResult result) {
		if (result.hasErrors()) {
			ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(cartService.save(cart));
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> get(@RequestParam String name) {
		log.info("Searching for the user of name:" + name);
		return ResponseEntity.ok(cartService.getdetails(name));
	}

}
