package prasun.springboot.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	@RequestMapping("/productFallback")
	public Mono<String> productFallback(){
		return Mono.just("Product service is taking too long to respond or is down. Please try again later.");
	}

	@RequestMapping("/priceFallback")
	public Mono<String> priceFallback(){
		return Mono.just("Price service is taking too long to respond or is down. Please try again later.");
	}
	
	@RequestMapping("/orderFallback")
	public Mono<String> orderFallback(){
		return Mono.just("Order service is taking too long to respond or is down. Please try again later.");
	}
	
	@RequestMapping("/cartFallback")
	public Mono<String> cartFallback(){
		return Mono.just("Cart service is taking too long to respond or is down. Please try again later.");
	}
	

}
