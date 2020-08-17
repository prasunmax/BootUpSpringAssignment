package prasun.springboot.productCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductCataLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCataLogServiceApplication.class, args);
	}

}
