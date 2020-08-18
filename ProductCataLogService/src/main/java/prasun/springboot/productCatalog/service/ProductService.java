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

	public ProductVO save(Product product) {
		ProductVO productVO = new ProductVO(saveRepo.save(product));
		Map<String, Double> hashMap = new HashMap<String, Double>();
		hashMap.put("price", productVO.getPrice());
		hashMap.put("product_id", Double.valueOf(productVO.getId()));
		hashMap.put("quantity", Double.valueOf(productVO.getQuantity()));
		sender.send(hashMap);

		return productVO;
	}

	public ProductListVO saveAll(List<Product> products) {
		List<ProductVO> lists = new ArrayList<ProductVO>();
		saveRepo.saveAll(products).forEach(product -> lists.add(new ProductVO(product)));
		return new ProductListVO(lists);
	}

	public void sendProductDetails(Integer productId) {
		Product product = getProductByProductId(productId);
		Map<String, Double> hashMap = new HashMap<String, Double>();
		hashMap.put("price", product.getPrice());
		hashMap.put("product_id", Double.valueOf(product.get_id()));
		hashMap.put("quantity", Double.valueOf(product.getQuantity() == 0 ? 100 : product.getQuantity()));
		sender.send(hashMap);
	}
}
