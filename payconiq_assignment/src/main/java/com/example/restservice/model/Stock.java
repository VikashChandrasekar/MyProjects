package com.example.restservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class Stock {
	private long id;
	private String name;
	private Amount currentPrice;
	private Timestamp lastUpdate;

	public Stock() {

	}

	/**
	 * This is args constructor for Stock
	 *
	 * @param id stock id
	 * @param name stock name
	 * @param currentPrice stock price
	 * @param lastUpdate stock last updated date
	 */
	public Stock(final long id, final String name, final Amount currentPrice, final Timestamp lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

}
