package br.com.gustavoleterio.market.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal unitaryPrice;
	private int quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	private Purchase purchase;
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	public PurchaseProduct() {
	}

	public PurchaseProduct(int quantity,Purchase purchase, Product product) {
		this.quantity = quantity;
		this.unitaryPrice = product.getPrice();
		this.product = product;
		this.purchase = purchase;
	}

	public BigDecimal getValue() {
		return unitaryPrice.multiply(new BigDecimal(quantity));
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(BigDecimal unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
