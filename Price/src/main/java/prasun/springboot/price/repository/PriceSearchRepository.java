package prasun.springboot.price.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prasun.springboot.price.entity.Price;

@Repository
public class PriceSearchRepository {

	private EntityManager entityManager;

	@Autowired
	public PriceSearchRepository(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<Price> findAll() {
		return entityManager.createQuery("select p from Price p", Price.class).getResultList();
	}

	public Price queryByPriceId(int PriceId) {
		return entityManager.find(Price.class, PriceId);
	}

	public Price queryByProductId(int product_id) {
		return entityManager.createQuery("select p from Price p where product_id = :product_id", Price.class)
				.setParameter("product_id", product_id).getSingleResult();
	}

}
