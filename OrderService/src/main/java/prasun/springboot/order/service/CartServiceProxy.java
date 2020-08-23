package prasun.springboot.order.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import prasun.springboot.order.VO.CartVO;

@FeignClient(name = "${microservices.endpoints.endpoint.cart.cart}")
@RibbonClient
public interface CartServiceProxy {
	
	@GetMapping(path="/api/cart/{name}")
	CartVO getCartDetails(@PathVariable String name);
	
}
