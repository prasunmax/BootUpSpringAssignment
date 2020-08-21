package prasun.springboot.productCatalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prasun.springboot.productCatalog.VO.ProductListVO;
import prasun.springboot.productCatalog.VO.ProductVO;
import prasun.springboot.productCatalog.entity.Product;
import prasun.springboot.productCatalog.repository.ProductRepository;
import prasun.springboot.productCatalog.repository.ProductSearchRepository;

@Service
public class ProductService {
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	private ProductRepository saveRepo;
	private ProductSearchRepository searchRepo;
	private SenderService sender;

	@Autowired
	public ProductService(ProductRepository saveRepo, ProductSearchRepository searchRepo, SenderService sender) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.sender = sender;
	}

	public ProductListVO findAll() {
		log.info("Finding all Products");
		List<ProductVO> lists = new ArrayList<ProductVO>();
		searchRepo.findAll().forEach(product -> lists.add(new ProductVO(product)));
		ProductListVO listVO = new ProductListVO(lists);
		return listVO;
	}

	public Product getProductByProductId(int productId) {
		log.info("Finding by Product id" + productId);
		return searchRepo.queryByProductId(productId);
	}

	public ProductVO queryByProductId(int productId) {
		log.info("Finding by Product id" + productId);
		return new ProductVO(searchRepo.queryByProductId(productId));
	}

	public List<Product> queryByProductsByName(String name) {
		return searchRepo.queryByProductsByName(name);
	}

	public ProductVO save(ProductVO product) {
		ProductVO productVO = new ProductVO(saveRepo.save(product.getFromVO()));
		Map<String, Double> hashMap = new HashMap<String, Double>();
		hashMap.put("price", product.getPrice());
		hashMap.put("product_id", Double.valueOf(productVO.getId()));
		hashMap.put("quantity", Double.valueOf(
				null == product.getQuantity() || product.getQuantity() == 0.0 ? 100.0 : product.getQuantity()));
		sender.send(hashMap);

		return productVO;
	}

	public ProductListVO saveAll(List<Product> products) {
		log.info("Product details are:" + products);
		List<ProductVO> lists = new ArrayList<ProductVO>();
//		saveRepo.saveAll(products).forEach(product -> lists.add(new ProductVO(product)));
//		Map<String, Double> hashMap = new HashMap<String, Double>();
//		for (ProductVO product : lists) {
//			hashMap.put("price", product.getPrice());
//			hashMap.put("product_id", Double.valueOf(product.getId()));
//			hashMap.put("quantity", Double.valueOf(product.getQuantity() == 0.0 ? 100.0 : product.getQuantity()));
//			sender.send(hashMap);
//		}
		products.forEach(product -> lists.add(save(new ProductVO(product))));
		return new ProductListVO(lists);
	}

	public void save(Map<String, Double> productDetails) {
		// If the product already exists then update it otherwise insert it

		Product product = getProductByProductId(productDetails.get("product_id").intValue());
		if (null != product) {
			product.setPrice(productDetails.get("price"));
			product.setQuantity(productDetails.get("quantity").intValue());
		}
		saveRepo.save(product);
	}

	public void saveOrderDetails(Map<String, Integer> productDetails) {
		Product product = getProductByProductId(productDetails.get("product_id"));
		if (null != product) {
			product.setQuantity(product.getQuantity() - productDetails.get("quantity"));
			saveRepo.save(product);
		}
	}
}
