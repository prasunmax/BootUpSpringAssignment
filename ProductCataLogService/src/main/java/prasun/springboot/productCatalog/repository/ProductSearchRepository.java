package prasun.springboot.productCatalog.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prasun.springboot.productCatalog.entity.Product;

@Repository
public class ProductSearchRepository {

	private EntityManager entityManager;

	@Autowired
	public ProductSearchRepository(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<Product> findAll() {
		return entityManager.createQuery("select r from Review r", Product.class).getResultList();
	}

	public Product queryByProductId(int productId) {
		return entityManager.find(Product.class, productId);
	}
	public List<Product> queryByProductsByName(String name) {

		return entityManager.createQuery(
				"select p from Product p where p.name like  %:name% ",
				Product.class).setParameter("name", name).getResultList();
	}

}
