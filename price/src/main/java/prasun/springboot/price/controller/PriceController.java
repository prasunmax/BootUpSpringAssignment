package prasun.springboot.price.controller;

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

import prasun.springboot.price.VO.PriceVO;
import prasun.springboot.price.service.PriceService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
	private PriceService PriceService;

	@Autowired
	public PriceController(PriceService PriceService) {
		super();
		this.PriceService = PriceService;
	}

	@GetMapping
	public ResponseEntity<?> getAllPrices() {
		return ResponseEntity.ok(PriceService.findAll());
	}

	@GetMapping("/{ProductId}")
	public ResponseEntity<?> getPrices(@PathVariable int ProductId) {
		return ResponseEntity.ok(PriceService.queryByProductId(ProductId));
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody PriceVO Price, BindingResult result) {
		if (result.hasErrors()) {
			ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(PriceService.save(Price.getFromVO()));
	}

}
