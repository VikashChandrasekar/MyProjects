package com.example.restservice.service;

import com.example.restservice.exception.RecordNotFoundException;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    List<Stock> stocks;
    MultiValuedMap<Stock, Amount> priceHistory;

    @InjectMocks
    StockService stockService;

    @Before
    public void setUp(){
        stocks = TestStub.createStocks();
        priceHistory = TestStub.createMultiValuedMap();
    }

    @Test(expected = RecordNotFoundException.class)
    public void testRecordNotFoundException(){
        stockService.getStocks();
    }

    @Test
    public void testGetStocks(){
        stockService.stocks = stocks;
        List<Stock> stocks1 = stockService.getStocks();
        Assert.assertEquals(1, stocks1.get(0).getId());
        Assert.assertEquals(2, stocks1.get(1).getId());
        Assert.assertEquals("Laptop", stocks1.get(0).getName());
        Assert.assertEquals("Mobile", stocks1.get(1).getName());
        Assert.assertEquals(new BigDecimal(1500), stocks1.get(0).getCurrentPrice().getAmount());
    }

    @Test
    public void testFindAllStocks(){
        stockService.stocks = stocks;
        List<Stock> stocks1 = stockService.findAllStocks();
        Assert.assertEquals(1, stocks1.get(0).getId());
        Assert.assertEquals(2, stocks1.get(1).getId());
        Assert.assertEquals("Laptop", stocks1.get(0).getName());
        Assert.assertEquals("Mobile", stocks1.get(1).getName());
        Assert.assertEquals(new BigDecimal(1500), stocks1.get(0).getCurrentPrice().getAmount());
    }

    @Test
    public void testFindStockById(){
        stockService.stocks = stocks;
        Stock stock = stockService.findStockById(1);
        Assert.assertEquals(1, stock.getId());
        Assert.assertEquals(new BigDecimal(1500), stock.getCurrentPrice().getAmount());
        Assert.assertEquals("Laptop", stock.getName());
    }

    @Test
    public void testFindStockHistoryById(){
        stockService.stocks = stocks;
        stockService.priceHistory = priceHistory;
        Collection<Amount> stock = stockService.findStockHistoryById(1);
        Assert.assertEquals(0, stock.size());
    }

    @Test
    public void testUpdateStockPrice(){
        stockService.stocks = stocks;
        Currency currency = Currency.getInstance("EUR");
        Amount amount1 = new Amount(new BigDecimal(2000), currency);
        Stock stock = stockService.updateStockPrice(amount1, 1);
        Assert.assertEquals(new BigDecimal(2000), stock.getCurrentPrice().getAmount());
        Assert.assertEquals("Laptop", stock.getName());
    }

    @Test
    public void tesCreateStocks(){
        stockService.stocks = stocks;
        Currency currency = Currency.getInstance("EUR");
        Amount amount1 = new Amount(new BigDecimal(300), currency);
        Stock stock = stockService.createStock(3, "Earphones", amount1);
        Assert.assertEquals(3, stock.getId());
        Assert.assertEquals("Earphones", stock.getName());
        Assert.assertEquals(new BigDecimal(300),stock.getCurrentPrice().getAmount());
    }




}
