package prasun.springboot.productCatalog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasun.springboot.productCatalog.VO.ProductVO;
import prasun.springboot.productCatalog.VO.ReviewVO;
import prasun.springboot.productCatalog.entity.Product;
import prasun.springboot.productCatalog.service.ProductService;
import prasun.springboot.productCatalog.service.ReviewService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private ProductService productService;
	private ReviewService reviewService;
	
	@Autowired
	public ProductController(ProductService productService, ReviewService reviewService) {
		super();
		this.productService = productService;
		this.reviewService = reviewService;
	}


	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}


	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProducts(@PathVariable int productId){
		return ResponseEntity.ok(productService.queryByProductId(productId));
	}

	@GetMapping("/byName/{productName}")
	public ResponseEntity<?> getProductsByName(@PathVariable String productName){
		return ResponseEntity.ok(productService.queryByProductsByName(productName));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ProductVO product, BindingResult result){
		if(result.hasErrors()) {
			ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(productService.save(product));
	}
	
	@PostMapping("/all")
	public ResponseEntity<?> save(@Valid @RequestBody List<ProductVO> productList, BindingResult result){
		if(result.hasErrors()) {
			ResponseEntity.badRequest();
		}
		List<Product> products = new ArrayList<Product>();
		productList.forEach(productVO -> products.add(productVO.getFromVO()));
		return ResponseEntity.ok(productService.saveAll(products));
	}
	@PostMapping("/{productId}/reviews")
	public ResponseEntity<?> saveReview( @PathVariable int productId, @Valid @RequestBody ReviewVO review, BindingResult result){
		if(result.hasErrors()) {
			ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(reviewService.save(review.getFromVO(), productId));
	}
	
	@GetMapping("/{productId}/reviews")
	public ResponseEntity<?> getReview( @PathVariable int productId){
		
		return ResponseEntity.ok(reviewService.queryByProductId( productId));
	}
}
