package br.com.gustavoleterio.market.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal totalValue = new BigDecimal(0);
	private LocalDate date = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
	private List<PurchaseProduct> items = new ArrayList<>();


	public Purchase() {
	}

	public Purchase(Client client) {
		this.client = client;
	}

	public void addProduct(PurchaseProduct op) {
		op.setPurchase(this);
		this.items.add(op);
		this.totalValue = this.totalValue.add(op.getValue());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "\nProduct -> {\n\tId = " + id + ",\n\t Date = " + date + ",\n\t Total Value = " + totalValue
				+ "\n\tClient = " + client + "\n}";
	}

}
