package prasun.springboot.price.service;

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
	private PriceService priceService;

	@Bean
	Queue queue() {
		return new Queue("ProductPriceQ", false);
	}

	@RabbitListener(queues = "ProductPriceQ")
	public void processMessage(Map<String, Double> priceDetails) {
		log.info("===========> ==== <===========");
		log.info("The Price Details sent is:" + priceDetails);
		log.info("===========> ==== <===========");

		priceService.save(priceDetails);
		// This call is just for validation of Rabbit MQ
	}
}
