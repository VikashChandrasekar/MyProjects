package com.example.restservice.helper;

import com.example.restservice.model.Amount;
import com.example.restservice.model.Stock;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

public class TestStub {

    public static List<Stock> createStocks(){
        List<Stock> stocks = new ArrayList<>();
        Currency currency = Currency.getInstance("EUR");
        Amount amount = new Amount(new BigDecimal(1500), currency);
        Stock stock = new Stock(1L, "Laptop", amount, new Timestamp(56));
        Currency currency1 = Currency.getInstance("EUR");
        Amount amount1 = new Amount(new BigDecimal(700), currency1);
        Stock stock1 = new Stock(2L, "Mobile", amount1, new Timestamp(56));
        stocks.add(stock);
        stocks.add(stock1);
        return stocks;
    }

    public static Stock createStock(){
        Currency currency = Currency.getInstance("EUR");
        Amount amount = new Amount(new BigDecimal(1500), currency);
        Stock stock = new Stock(1L, "Laptop", amount, new Timestamp(56));
        return stock;
    }

    public static Collection<Amount> createAmount() {
        Collection<Amount> amounts = new ArrayList<>();
        Currency currency = Currency.getInstance("EUR");
        Amount amount = new Amount(new BigDecimal(1500), currency);
        Amount amount1 = new Amount(new BigDecimal(2000), currency);
        amounts.add(amount);
        amounts.add(amount1);
        return amounts;
    }

    public static MultiValuedMap<Stock, Amount> createMultiValuedMap(){
        MultiValuedMap<Stock, Amount> stockAmountMultiValuedMap = new ArrayListValuedHashMap<>();
        Currency currency = Currency.getInstance("EUR");
        Amount amount = new Amount(new BigDecimal(1500), currency);
        Stock stock = new Stock(1L, "Laptop", amount, new Timestamp(56));
        stockAmountMultiValuedMap.put(stock, amount);
        return stockAmountMultiValuedMap;
    }
}
