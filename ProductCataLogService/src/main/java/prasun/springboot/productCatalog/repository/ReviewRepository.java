package prasun.springboot.productCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prasun.springboot.productCatalog.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	

}
