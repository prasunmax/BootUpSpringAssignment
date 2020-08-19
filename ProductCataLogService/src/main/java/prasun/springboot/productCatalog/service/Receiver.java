package prasun.springboot.productCatalog.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Receiver {
	private static final Logger log = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	private ProductService productService;

	@Bean
	Queue queue() {
		return new Queue("PriceProductQ", false);
	}

	@RabbitListener(queues = "PriceProductQ")
	public void processMessage(Map<String, Double> productDetails) {
		log.info("===========> ==== <===========");
		log.info("The Product Details is asked for:" + productDetails);
		log.info("===========> ==== <===========");

		productService.save(productDetails);
	}
}
