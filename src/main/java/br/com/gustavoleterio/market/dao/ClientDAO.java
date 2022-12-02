package br.com.gustavoleterio.market.dao;

import javax.persistence.EntityManager;

import br.com.gustavoleterio.market.model.Client;
import br.com.gustavoleterio.market.model.Product;

public class ClientDAO {

	private EntityManager entityManager;

	public ClientDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void register(Client client) {
		entityManager.persist(client);
	}

	public Client findItemById(Long id) {
		return entityManager.find(Client.class, id);
	}

}
