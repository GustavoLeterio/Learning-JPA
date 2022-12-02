package br.com.gustavoleterio.market.vo;

import java.time.LocalDate;

public class SalesReportVO {
	private String name;
	private long quantity;
	private LocalDate date;

	public SalesReportVO(String name, long quantity, LocalDate date) {
		this.name = name;
		this.quantity = quantity;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public long getQuantity() {
		return quantity;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return name + "\t" + quantity + "\t " + date + "\n";
	}

	
}
