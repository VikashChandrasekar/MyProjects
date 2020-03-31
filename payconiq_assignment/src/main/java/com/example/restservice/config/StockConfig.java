package com.example.restservice.config;

import com.example.restservice.model.Amount;
import com.example.restservice.model.Stock;
import com.example.restservice.stockservice.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * This StockConfig class run before the start
 * of the application and creates list of
 * stock in the memory of the application
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-31
 *
 */

@Configuration
public class StockConfig {

    @Autowired
    private StockService stockService;

    /**
     * This method will create list of stock in memory on application startup
     *
     * @return List of Stocks
     */
    @Bean
    public List<Stock> mapStockValues(){
        List<Stock> stockList = new ArrayList<>();
        Currency currency = Currency.getInstance("EUR");
        Amount amount = new Amount(1500.0, currency);
        Stock stock = new Stock(1, "Laptop", amount, new Timestamp(56));
        Currency currency1 = Currency.getInstance("EUR");
        Amount amount1 = new Amount(700.0, currency1);
        Stock stock1 = new Stock(2, "Mobile", amount1, new Timestamp(56));
        stockList.add(stock);
        stockList.add(stock1);
        stockService.stocks = stockList;
        return stockList;
    }
}
