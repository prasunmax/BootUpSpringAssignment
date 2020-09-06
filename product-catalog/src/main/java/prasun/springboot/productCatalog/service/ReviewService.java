package prasun.springboot.productCatalog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prasun.springboot.productCatalog.VO.ReviewResponseVO;
import prasun.springboot.productCatalog.VO.ReviewVO;
import prasun.springboot.productCatalog.entity.Review;
import prasun.springboot.productCatalog.repository.ReviewRepository;
import prasun.springboot.productCatalog.repository.ReviewSearchRepository;

@Service
public class ReviewService {
	private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

	private ReviewRepository saveRepo;
	private ReviewSearchRepository searchRepo;
	private ProductService productService;

	@Autowired
	public ReviewService(ReviewRepository saveRepo, ReviewSearchRepository searchRepo, ProductService productService) {
		super();
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.productService = productService;
	}


	public ReviewResponseVO queryByProductId(int productId) {
		log.info("Finding by Product id:" + productId);
		return new ReviewResponseVO(searchRepo.queryByProductId(productId));
	}



	public ReviewVO save(Review review, int productId) {
		review.setProduct(productService.getProductByProductId(productId));
		return new ReviewVO(saveRepo.save(review));
	}
}
