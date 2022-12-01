package br.com.gustavoleterio.market.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

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
		return entityManager.createQuery("SELECT p.price FROM Product AS p WHERE p.name= :name", BigDecimal.class)
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
}
