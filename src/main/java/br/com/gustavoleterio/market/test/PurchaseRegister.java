package br.com.gustavoleterio.market.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import java.util.List;
import br.com.gustavoleterio.market.dao.CategoryDAO;
import br.com.gustavoleterio.market.dao.ClientDAO;
import br.com.gustavoleterio.market.dao.ProductDAO;
import br.com.gustavoleterio.market.dao.PurchaseDAO;
import br.com.gustavoleterio.market.model.Client;
import br.com.gustavoleterio.market.model.Product;
import br.com.gustavoleterio.market.model.Purchase;
import br.com.gustavoleterio.market.model.PurchaseProduct;
import br.com.gustavoleterio.market.util.JPAUtil;
import br.com.gustavoleterio.market.vo.SalesReportVO;

public class PurchaseRegister {

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProductDAO productDAO = new ProductDAO(entityManager);
		CategoryDAO categoryDAO = new CategoryDAO(entityManager);
		ClientDAO clientDAO = new ClientDAO(entityManager);

		entityManager.getTransaction().begin();

		List<Product> products = new ArrayList<>();

		products.add(ProductRegister.register(productDAO, categoryDAO, "Xiaomi Redmi", "Crazy Smartphone",
				new BigDecimal("800"), "Smartphone"));
		products.add(ProductRegister.register(productDAO, categoryDAO, "Headset Razer", "Crazy Headset",
				new BigDecimal("200"), "Headset"));
		products.add(ProductRegister.register(productDAO, categoryDAO, "Xiaomi Redmi", "Crazy Smartphone",
				new BigDecimal("800"), "Smartphone"));

		Client client = new Client("Gustavo", "123456789");
		clientDAO.register(client);

		Purchase purchase = new Purchase(client);

		products.forEach(product -> purchase.addProduct(new PurchaseProduct(10, purchase, product)));

		PurchaseDAO purchaseDAO = new PurchaseDAO(entityManager);
		purchaseDAO.register(purchase);

		entityManager.getTransaction().commit();

		List<SalesReportVO> report = purchaseDAO.salesReport();

		System.out.println("Product\t\tQuantity Last Date Bought");
		report.forEach(System.out::print);

		Purchase newPurchase = purchaseDAO.findPurchaseAndClient(1l);
		entityManager.close();

		System.out.println("Client Name: " + newPurchase.getClient().getName());

	}

}
