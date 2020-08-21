package prasun.springboot.order.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import prasun.springboot.order.entity.Order;

@Repository
public class OrderSearchRepository {

	private EntityManager entityManager;

	@Autowired
	public OrderSearchRepository(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	public List<Order> findAll() {
		return entityManager.createQuery("select o from Order o", Order.class).getResultList();
	}

	public Order queryByOrderId(int OrderId) {
		return entityManager.find(Order.class, OrderId);
	}

	public List<Order> queryByProductId(int product_id) {
		//OgmSession session = entityManager.unwrap(OgmSession.class);
		return entityManager.createQuery("select o from Order o join o.orderItems where o.product_id = :product_id", Order.class)
				.setParameter("product_id", product_id).getResultList();
	}

}
