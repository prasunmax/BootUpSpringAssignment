package prasun.springboot.cart.service;

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
	private CartService cartService;

	@Bean
	Queue orderCartQ() {
		return new Queue("OrderCartQ", false);
	}
	
	@RabbitListener(queues = "OrderCartQ")
	public void processOrderMessage(Map<String, String> removeOrder) {
		log.info("===========> ==== <===========");
		log.info("The Cart Details being sent:" + removeOrder);
		log.info("===========> ==== <===========");

		cartService.saveOrderDetails(removeOrder);
	}
}
