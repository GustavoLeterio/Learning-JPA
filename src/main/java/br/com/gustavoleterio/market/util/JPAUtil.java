package br.com.gustavoleterio.market.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//The Entity Manager Factory should be unique,so I create it as a final.
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("market");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
}
