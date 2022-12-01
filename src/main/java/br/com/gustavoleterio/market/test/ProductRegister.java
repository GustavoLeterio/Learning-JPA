package br.com.gustavoleterio.market.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import java.util.List;
import br.com.gustavoleterio.market.dao.CategoryDAO;
import br.com.gustavoleterio.market.dao.ProductDAO;
import br.com.gustavoleterio.market.model.Category;
import br.com.gustavoleterio.market.model.Product;
import br.com.gustavoleterio.market.util.JPAUtil;

public class ProductRegister {
	public static void main(String[] args) {
		productRegister();

		Long id = 1l;
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(entityManager);

		Product product = productDAO.findItemById(id);
		System.out.println(product);

		BigDecimal price = productDAO.findPriceWithName("Xiaomi Redmi");
		System.out.println("\nXIAOMI REDMI PRICE =>" + price + "\n");

		List<Product> products = productDAO.listItems();
		products.forEach(System.out::println);

		products = productDAO.findItemsByNameSubstring("phone");
		products.forEach(System.out::println);

		products = productDAO.findItemsByCategory("Smartphone");
		products.forEach(System.out::println);

		products = productDAO.findItemsByCategory("Smartphone");
		products.forEach(System.out::println);

	}

	private static void productRegister() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(entityManager);
		CategoryDAO categoryDAO = new CategoryDAO(entityManager);

		entityManager.getTransaction().begin();

		Category category = new Category("Smartphone");
		Product product = new Product("Xiaomi Redmi", "One of the best chinese smartphone!", new BigDecimal(4654.85),
				category);

		categoryDAO.register(category);
		productDAO.register(product);

		category = new Category("Headphone");
		product = new Product("Asus Headphone", "7.1 Surround!", new BigDecimal(4654.85), category);

		categoryDAO.register(category);
		productDAO.register(product);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
