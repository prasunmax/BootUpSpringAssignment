package prasun.springboot.price.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	private RestTemplate template;

	@Autowired
	public PriceService(PriceRepository saveRepo, PriceSearchRepository searchRepo, RestTemplate template) {
		this.saveRepo = saveRepo;
		this.searchRepo = searchRepo;
		this.template = template;
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

	public PriceVO queryByPriceId(int PriceId) {
		log.info("Finding by Price id" + PriceId);
		return new PriceVO(searchRepo.queryByPriceId(PriceId));
	}

	public PriceVO save(Price price) {
		Price priceTemp = searchRepo.queryByProductId(price.getProduct_id());
		if (null != priceTemp) {
			price.set_id(priceTemp.get_id());
		}
		//Check if the product is present or not
		ProductVO product = template.getForObject(templateVal + "/" + price.getProduct_id(), ProductVO.class);
		if (null == product || null == product.getId()) {
			return new PriceVO();
		}
		return new PriceVO(saveRepo.save(price));
	}

	public PriceListVO saveAll(List<Price> Prices) {
		List<PriceVO> lists = new ArrayList<PriceVO>();
		saveRepo.saveAll(Prices).forEach(Price -> lists.add(new PriceVO(Price)));
		return new PriceListVO(lists);
	}

	public PriceVO save(Map<String, Double> priceDetails) {
		// If the product already exists then update it otherwise insert it
		Price price = new Price(priceDetails.get("price"), priceDetails.get("product_id").intValue(),
				priceDetails.get("quantity").intValue());
		Price priceTemp = searchRepo.queryByProductId(priceDetails.get("product_id").intValue());
		if (null != priceTemp)
			price.set_id(priceTemp.get_id());
		return new PriceVO(saveRepo.save(price));
	}
}
