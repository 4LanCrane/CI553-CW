package clients.cashier;

import catalogue.Basket;
import catalogue.Product;
import dbAccess.StockR;
import dbAccess.StockRW;
import debug.DEBUG;
import middle.StockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CashierModelTest {

    @Test
    void getBasket() {
    }

    @Test
    void doCheck() {
    }

    @Test
    void doBuy() {

    }

    @Test
    void doClearBasket() {
        try {
            StockRW theStock = new StockRW();
            Basket theBasket = new Basket();
            theBasket.add(theStock.getDetails("0003"));
            theBasket.add(theStock.getDetails("0004"));
            theBasket.add(theStock.getDetails("0005"));


            for (Product pr : theBasket) {
                theStock.addStock(pr.getProductNum(), pr.getQuantity());
                Assertions.assertEquals(1, theStock.getDetailsByName(pr.getDescription()).size());
            }

            Assertions.assertEquals(3, theBasket.size());
            theBasket.clear();
            Assertions.assertEquals(0, theBasket.size());

        } catch (StockException e) {
            DEBUG.error("%s\n%s",
                    "CashierModel.doBuy", e.getMessage());
            e.getMessage();
        }
    }

    @Test
    void doBought() {
    }

    @Test
    void askForUpdate() {
    }

    @Test
    void makeBasket() {
    }
}