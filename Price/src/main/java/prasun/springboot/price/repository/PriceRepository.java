package prasun.springboot.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prasun.springboot.price.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
	
	

}
