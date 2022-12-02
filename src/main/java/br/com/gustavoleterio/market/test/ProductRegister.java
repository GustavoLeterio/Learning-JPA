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
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(entityManager);
		CategoryDAO categoryDAO = new CategoryDAO(entityManager);

		entityManager.getTransaction().begin();
		register(productDAO, categoryDAO, "Xiaomi Redmi", "One of the best Smartphone!", new BigDecimal(1659.99),
				"Smartphone");
		register(productDAO, categoryDAO, "Asus Headset", "7.1 Surround!", new BigDecimal(399.99), "Headset");
		entityManager.getTransaction().commit();

		Long id = 1l;

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

		products = productDAO.findByParameterWithCriteria("Xiaomi Redmi", null, null);
		products.forEach(System.out::println);
		
		entityManager.close();

	}

	public static Product register(ProductDAO productDAO, CategoryDAO categoryDAO, String pName, String pDesc,
			BigDecimal pPrice, String cName) {

		Category category = new Category(cName);
		Product product = new Product(pName, pDesc, pPrice, category);

		categoryDAO.register(category);
		productDAO.register(product);

		return product;
	}
}
