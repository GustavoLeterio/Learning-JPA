package br.com.gustavoleterio.market.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.gustavoleterio.market.model.Product;

public class ProductDAO {

	private EntityManager entityManager;

	public ProductDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void register(Product product) {
		entityManager.persist(product);
	}

	public Product findItemById(Long id) {
		return entityManager.find(Product.class, id);
	}

	public List<Product> findItemsByCategory(String categoryName) {
		return entityManager
				.createQuery("SELECT p FROM Product AS p WHERE p.category.name = :categoryName", Product.class)
				.setParameter("categoryName", categoryName).getResultList();
	}

	public BigDecimal findPriceWithName(String name) {
		return entityManager.createQuery("SELECT p.price FROM Product AS p WHERE p.name = :name", BigDecimal.class)
				.setParameter("name", name).getSingleResult();
	}

	public List<Product> findItemsByNameSubstring(String substring) {
		return entityManager
				.createQuery("SELECT p FROM Product AS p WHERE p.name LIKE CONCAT('%', :substring ,'%')", Product.class)
				.setParameter("substring", substring).getResultList();
	}

	public List<Product> listItems() {
		// Creating query(Selecting Everything from Entity Product, Typing list
		// return).returningList
		return entityManager.createQuery("SELECT p FROM Product AS p", Product.class).getResultList();
	}

	public List<Product> findByParameter(String name, BigDecimal price, LocalDate date) {
		TypedQuery<Product> qry = entityManager.createQuery("SELECT p FROM Product AS p WHERE 1=1 "
				+ (name != null ? "AND p.nome = :nome " : " ") + (price != null ? "AND p.price = :price " : " ")
				+ (date != null ? "AND p.date = :date " : " "), Product.class);
		if (name != null)
			qry.setParameter("name", name);
		if (price != null)
			qry.setParameter("price", price);
		if (date != null)
			qry.setParameter("date", date);

		return qry.getResultList();
	}

	public List<Product> findByParameterWithCriteria(String name, BigDecimal price, LocalDate date) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> from = query.from(Product.class);

		Predicate filters = builder.and();

		if (name != null && !name.trim().isEmpty()) {
			filters = builder.and(filters, builder.equal(from.get("name"), name));
		}
		if (price != null) {
			filters = builder.and(filters, builder.equal(from.get("price"), price));
		}
		if (date != null) {
			filters = builder.and(filters, builder.equal(from.get("date"), date));
		}
		query.where(filters);

		return entityManager.createQuery(query).getResultList();
	}
}
