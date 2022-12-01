package br.com.gustavoleterio.market.dao;

import javax.persistence.EntityManager;
import br.com.gustavoleterio.market.model.Category;

public class CategoryDAO {

	private EntityManager entityManager;

	public CategoryDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void register(Category category) {
		entityManager.persist(category);
	}

}
