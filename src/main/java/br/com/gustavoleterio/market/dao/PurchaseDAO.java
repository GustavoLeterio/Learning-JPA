package br.com.gustavoleterio.market.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.gustavoleterio.market.model.Purchase;
import br.com.gustavoleterio.market.vo.SalesReportVO;

public class PurchaseDAO {

	private EntityManager entityManager;

	public PurchaseDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void register(Purchase order) {
		entityManager.persist(order);
	}

	public BigDecimal sumTotalPrice() {
		return entityManager.createQuery("SELECT SUM(P.PRICE) FROM PURCHASE AS P", BigDecimal.class).getSingleResult();
	}

	public Purchase findPurchaseAndClient(long id) {
		return entityManager.createQuery("SELECT p FROM Purchase as p JOIN FETCH p.client WHERE p.id = :id", Purchase.class)
				.setParameter("id", id).getSingleResult();
	}

	public List<SalesReportVO> salesReport() {
		return entityManager.createQuery(
				"SELECT new br.com.gustavoleterio.market.vo.SalesReportVO(" + "product.name, " + "SUM(item.quantity), "
						+ "MAX(purchase.date)" + ") " + "FROM Purchase AS purchase " + "JOIN purchase.items AS item "
						+ "JOIN item.product AS product " + "GROUP BY product.name " + "ORDER BY item.quantity DESC",
				SalesReportVO.class).getResultList();
	}

}
