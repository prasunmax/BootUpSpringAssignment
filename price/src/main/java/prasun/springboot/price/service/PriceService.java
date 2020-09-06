package prasun.springboot.price.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import prasun.springboot.price.VO.PriceListVO;
import prasun.springboot.price.VO.PriceVO;
import prasun.springboot.price.VO.ProductVO;
import prasun.springboot.price.entity.Price;
import prasun.springboot.price.repository.PriceRepository;
import prasun.springboot.price.repository.PriceSearchRepository;

@Service
public class PriceService {
	private static final Logger log = LoggerFactory.getLogger(PriceService.class);

	private PriceRepository saveRepo;
	private PriceSearchRepository searchRepo;
	@Value("${microservices.endpoints.endpoint.productCatalog.products}")
	private String templateVal;
	private ProductServiceProxy productServiceProxy;
	private SenderService sender;

	@Autowired
	public PriceService(PriceRepository saveRepo, PriceSearchRepository searchRepo,
			ProductServiceProxy productServiceProxy, SenderService sender) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.productServiceProxy = productServiceProxy;
		this.sender = sender;
	}

	public PriceListVO findAll() {
		log.info("Finding all Prices");
		List<PriceVO> lists = new ArrayList<PriceVO>();
		searchRepo.findAll().forEach(Price -> lists.add(new PriceVO(Price)));
		PriceListVO listVO = new PriceListVO(lists);
		return listVO;
	}

	public Price getPriceByPriceId(int PriceId) {
		log.info("Finding by Price id" + PriceId);
		return searchRepo.queryByPriceId(PriceId);
	}

	public PriceVO queryByProductId(int productId) {
		log.info("Finding by Price id" + productId);
		Price price = null;
		try {
			price = searchRepo.queryByProductId(productId);
		} catch (NoResultException nre) {

		}
		if (null == price) {
			// Check if the price is present in product, then add it in the Price table
			// ProductVO product = template.getForObject(templateVal + "/" + productId,
			// ProductVO.class);
			ProductVO product = productServiceProxy.getProduct(productId);
			if (null != product.getId()) {
				price = new Price(product);
				price = saveRepo.save(price);
			}
		}
		return new PriceVO(price);
	}

	public PriceVO save(Price price) {
		Price priceTemp = searchRepo.queryByProductId(price.getProduct_id());
		if (null != priceTemp)
			price.set_id(priceTemp.get_id());
		PriceVO pricevo = new PriceVO(saveRepo.save(price));
		Map<String, Double> hashMap = new HashMap<String, Double>();
		hashMap.put("price", pricevo.getPrice());
		hashMap.put("product_id", Double.valueOf(pricevo.getProduct_id()));
		hashMap.put("quantity", Double.valueOf(pricevo.getQuantity()));
		sender.send(hashMap);
		return new PriceVO(saveRepo.save(price));
	}

	public void save(Map<String, Double> priceDetails) {
		// If the product already exists then update it otherwise insert it
		Price price = new Price(priceDetails.get("price"), priceDetails.get("product_id").intValue(),
				priceDetails.get("quantity").intValue());
		Price priceTemp = searchRepo.queryByProductId(priceDetails.get("product_id").intValue());
		if (null != priceTemp)
			price.set_id(priceTemp.get_id());
		saveRepo.save(price);
	}
}
