package prasun.springboot.order.service;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
	
	@Autowired
	private RabbitMessagingTemplate template;
	
	@Bean
	Queue orderProductQ() {
		return new Queue("OrderProductQ", false);
	}
	
	@Bean
	Queue orderCartQ() {
		return new Queue("OrderCartQ", false);
	}
	
	public void sendToProduct(Object map){
		template.convertAndSend("OrderProductQ", map);
	}
	
	public void sendToCart(Object map){
		template.convertAndSend("OrderCartQ", map);
	}

}
