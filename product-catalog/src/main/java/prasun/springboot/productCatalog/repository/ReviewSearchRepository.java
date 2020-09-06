package prasun.springboot.productCatalog.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ogm.OgmSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prasun.springboot.productCatalog.entity.Review;

@Repository
public class ReviewSearchRepository {

	private EntityManager entityManager;

	@Autowired
	public ReviewSearchRepository(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Review> queryByProductId(int productId) {
		OgmSession ogmSession = entityManager.unwrap(OgmSession.class);
		String query = "{'product_id': " + productId + "}";
		//ogmSession.createNativeQuery(query).addEntity(Review.class).list();
		return ogmSession.createNativeQuery(query).addEntity(Review.class).list();
	}

}
