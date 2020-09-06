package prasun.springboot.productCatalog.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prasun.springboot.productCatalog.entity.Product;

@Repository
public class ProductSearchRepository {

	private EntityManagerFactory entityManagerFactory;

	@Autowired
	public ProductSearchRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public List<Product> findAll() {
		/*
		 * Entity Manager at class level will create a level 1 caching and this service be distributed so will not help while saving, 
		 * so have the entity-manager in local, get the details and close before leaving  
		 */
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Product> products = entityManager.createQuery("select p from Product p", Product.class).getResultList();
		entityManager.close();
		return products;
	}

	public Product queryByProductId(int productId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Product product = entityManager.find(Product.class, productId);
		entityManager.close();
		return product;
	}

	public List<Product> queryByProductsByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Product> products = entityManager
				.createQuery("select p from Product p where p.name like  %:name% ", Product.class)
				.setParameter("name", name).getResultList();
		entityManager.close();
		return products;
	}

}
