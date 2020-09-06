package prasun.springboot.price.service;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
	
	@Autowired
	private RabbitMessagingTemplate template;
	
	@Bean("SenderQueue")
	Queue queue() {
		return new Queue("PriceProductQ", false);
	}
	
	public void send(Object map){
		template.convertAndSend("PriceProductQ", map);
	}

}
