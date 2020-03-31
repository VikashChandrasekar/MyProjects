package com.example.restservice.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Builder
@Getter
public class Amount {
	private BigDecimal amount;
	private Currency currencyCode;
	
	public Amount() {
		
	}

	/**
	 * @param amount - amount of stock
	 * @param currencyCode - currency code
	 */
	public Amount(final BigDecimal amount, final Currency currencyCode) {
		super();
		this.amount = amount;
		this.currencyCode = currencyCode;
	}
}
