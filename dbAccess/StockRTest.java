package dbAccess;

import catalogue.Basket;
import catalogue.Product;
import middle.StockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockRTest {


    @Test
    @DisplayName("should pass as item is added to basket")
    void AddToBasket() throws StockException {
        Basket basket = new Basket();
        Product product = new Product("Test","Test",1,1);
        basket.add(product);
        assertEquals(product,basket.get(0));
    }

    @Test
    void Add() {
        Basket basket = new Basket();
        Product product = new Product("Test","Test",1,1);
        basket.add(product);
        Product product1 = new Product("Test2","Test2",2,2);
        basket.add(product1);
        assertEquals(product1 ,basket.get(1));
    }

    @Test
    @DisplayName("should fail as item name is not complete")
    void getDetailsByNameTest1() throws StockException {
        String productName = "Toast";
        StockR stockR = new StockR();
        assertEquals(productName,stockR.getDetailsByName(productName).get(0).getDescription());
    }

    @Test
    @DisplayName("should pass as item name is complete")
    void getDetailsByNameTest2() throws StockException {
        String productName = "Toaster";
        StockR stockR = new StockR();
        assertEquals(productName,stockR.getDetailsByName(productName).get(0).getDescription());
    }

    @Test
    @DisplayName("should pass as item Toaster exists")
    void existsByNameTest1() throws StockException {
        String productName = "Toast";
        StockR stockR = new StockR();
        assertTrue(stockR.existsByName(productName));
    }

    @Test
    @DisplayName("should pass as should return all items as item name is empty ")
    void existsByNameTest2() throws StockException {
        String productName = "";
        StockR stockR = new StockR();
        assertTrue(stockR.existsByName(productName));
    }


    @Test
    @DisplayName("should pass as basket is cleared")
    void ClearBasket() throws StockException {
        Basket basket = new Basket();
        Product product = new Product("Test","Test",1,1);
        basket.add(product);
        Product product1 = new Product("Test2","Test2",2,2);
        basket.add(product1);
        basket.clear();
        assertEquals(0,basket.size());

    }





}