package com.example.restservice.controller;


import com.example.restservice.helper.TestStub;
import com.example.restservice.model.Amount;
import com.example.restservice.model.Stock;
import com.example.restservice.stockservice.StockService;
import org.apache.commons.collections4.MultiValuedMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class StockControllerTest {

	@Mock
	private StockService stockService;

	@InjectMocks
	private StockController stockController;

	MultiValuedMap<Stock, Amount> priceHistory;

	List<Stock> stocks;


	@Before
	public void setUp(){
		Mockito.when(stockService.findAllStocks()).thenReturn(TestStub.createStocks());
		Mockito.when(stockService.findStockById(1)).thenReturn(TestStub.createStock());
		Mockito.when(stockService.findStockHistoryById(1)).thenReturn(TestStub.createAmount());
		priceHistory = TestStub.createMultiValuedMap();
		stocks = TestStub.createStocks();
	}


	@Test
	public void testFindAllStocks() {
		List<Stock> stocks = stockController.findAllStocks();
		Assert.assertEquals(1, stocks.get(0).getId());
		Assert.assertEquals(2, stocks.get(1).getId());
		Assert.assertEquals("Laptop", stocks.get(0).getName());
		Assert.assertEquals("Mobile", stocks.get(1).getName());
		Assert.assertEquals(1500.0, stocks.get(0).getCurrentPrice().getAmount(), 1500);
	}

	@Test
	public void testFindStock(){
		stockService.stocks = stocks;
		Stock stock = stockController.findStock(1);
		Assert.assertEquals(1, stock.getId());
		Assert.assertEquals(1500.0, stock.getCurrentPrice().getAmount(), 1500);
		Assert.assertEquals("Laptop", stock.getName());
	}

	@Test
	public void testFindStockHistoryById(){
		stockService.stocks = stocks;
		stockService.priceHistory = priceHistory;
		Collection<Amount> stock = stockController.findStockHistoryById(1);
		Assert.assertEquals(2, stock.size());
	}
}
