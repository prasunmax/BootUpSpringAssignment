package prasun.springboot.productCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prasun.springboot.productCatalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
