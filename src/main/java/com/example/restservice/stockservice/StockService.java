package com.example.restservice.stockservice;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.restservice.exception.RecordNotFoundException;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Amount;
import com.example.restservice.model.Stock;

/**
 * This StockService is a service class
 * contains all the logic to update, fetch stocks
 *
 * @author Vikash Chandrasekar
 * @version 1.0
 * @since 2020-03-30
 */


@Service
public class StockService {

    public List<Stock> stocks;
    public MultiValuedMap<Stock, Amount> priceHistory = new ArrayListValuedHashMap();

    /**
     * @return the stocks
     */
    public List<Stock> getStocks() {
        if (stocks == null) {
            throw new RecordNotFoundException("Stocks not found");
        }
        return stocks;
    }

    /**
     * Retrieves all the stocks.
     *
     * @return list of stocks
     */
    public List<Stock> findAllStocks() {
        return getStocks();
    }

    /**
     * Retrieves the stock for the given id.
     *
     * @param id the stock id
     */
    public Stock findStockById(final long id) {
        Optional<Stock> matchedStock = getStocks().stream().filter(stock -> stock.getId() == id).findFirst();
        if (!matchedStock.isPresent()) {
            throw new RecordNotFoundException("Stock not found for the id : " +id);
        }
        return matchedStock.get();
    }

    /**
     * Retrieves the stock history for the given id.
     *
     * @param id the stock id
     */
    public Collection<Amount> findStockHistoryById(final long id) {
        Optional<Stock> matchedStock = getStocks().stream().filter(stock -> stock.getId() == id).findFirst();
        if (!matchedStock.isPresent()) {
            throw new RecordNotFoundException("History not found for the id: " +id);
        }
        return priceHistory.get(matchedStock.get());
    }

    /**
     * Updates the price of a stock.
     *
     * @param amount {@link Amount}
     * @param id     the stock id
     * @return {@link Stock}
     */
    public Stock updateStockPrice(final Amount amount, final long id) {
        Optional<Stock> matchedStock = getStocks().stream().filter(stock -> stock.getId() == id).findFirst();
        if (matchedStock.isPresent()) {
            Stock stock = matchedStock.get();
            stock.setCurrentPrice(amount);
            stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            priceHistory.put(stock, amount);
        } else {
            throw new RecordNotFoundException("Cannot update the id : " +id);
        }
        return matchedStock.get();
    }

    /**
     * Creates the new stock
     *
     * @param id     the stock id
     * @param name   the stock name
     * @param amount {@link Amount}
     * @return {@link Stock}
     */
    public Stock createStock(final long id, final String name, final Amount amount) {
        Stock newStock = new Stock(id, name, amount, new Timestamp(System.currentTimeMillis()));
        getStocks().add(newStock);
        priceHistory.put(newStock, amount);
        return newStock;
    }
}
