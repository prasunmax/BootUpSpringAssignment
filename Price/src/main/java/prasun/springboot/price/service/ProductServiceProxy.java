package prasun.springboot.price.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import prasun.springboot.price.VO.ProductVO;

@FeignClient(name = "product-catalog-service")
public interface ProductServiceProxy {
	
	@GetMapping(path="/api/products/{productId}")
	ProductVO getProduct(@PathVariable int productId);
	
}
