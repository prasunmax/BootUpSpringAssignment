package prasun.springboot.productCatalog.service;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	public ProductService(ProductRepository saveRepo, ProductSearchRepository searchRepo) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
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
		return new ProductVO(saveRepo.save(product));
	}

	public ProductListVO saveAll(List<Product> products) {
		List<ProductVO> lists = new ArrayList<ProductVO>();
		saveRepo.saveAll(products).forEach(product -> lists.add(new ProductVO(product)));
		return new ProductListVO(lists);
	}
}
