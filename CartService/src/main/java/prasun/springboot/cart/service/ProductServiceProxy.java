package prasun.springboot.cart.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import prasun.springboot.cart.VO.ProductVO;

@FeignClient(name = "${microservices.endpoints.endpoint.productCatalog.products}")
@RibbonClient
public interface ProductServiceProxy {
	
	@GetMapping(path="/api/products/{productId}")
	ProductVO getProduct(@PathVariable int productId);
	
}
