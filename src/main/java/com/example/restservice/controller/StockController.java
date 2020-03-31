package com.example.restservice.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Amount;
import com.example.restservice.model.Stock;
import com.example.restservice.stockservice.StockService;

/**
 * This StockController is a REST controller class which
 * specifies all the REST end points
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-30
 *
 */

@RestController
@RequestMapping(value = "/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	/**
	 * This method returns all the list of stocks available
	 *
	 * @return List of Stocks
	 */
	@GetMapping
	public List<Stock> findAllStocks() {
		return stockService.findAllStocks();
	}

	/**
	 * This method helps us to get the single stock based on Id
	 *
	 * @param id -  This is the stock id
	 * @return Object - This returns single Stock object
	 */
	@GetMapping("/{id}")
	public Stock findStock(@PathVariable final long id) {
		return stockService.findStockById(id);
	}

	/**
	 * This method helps to get the history of changes happened in a stock
	 *
	 * @param id -  This is the stock id
	 * @return Collections - This returns the Collection of Amount object
	 */
	@GetMapping("/{id}/history")
	public Collection<Amount> findStockHistoryById(@PathVariable final long id) {
		return stockService.findStockHistoryById(id);
	}

	/**
	 * This method helps to update the stock price using its id
	 *
	 * @param amount - Amount object
	 * @param id - This is the stock id
	 * @return Object - This returns single stock object
	 */

	@PutMapping("/{id}")
	public Stock updateStockPrice(@RequestBody final Amount amount, @PathVariable final long id) {
		return stockService.updateStockPrice(amount, id);
	}

	/**
	 * This method helps to create new stock
	 *
	 * @param stock - Stock object
	 * @return Object - This returns single stock object
	 */
	@PostMapping
	public Stock createStock(@RequestBody final Stock stock) {
		return stockService.createStock(stock.getId(), stock.getName(), stock.getCurrentPrice());
	}
}
