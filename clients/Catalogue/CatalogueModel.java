package clients.Catalogue;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockException;
import middle.StockReader;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Implements the Model of the customer client
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */
public class CatalogueModel extends Observable
{
    private Product     theProduct = null;          // Current product
    private Basket      theBasket  = null;          // Bought items

    private String      pn = "";                    // Product being processed

    private StockReader     theStock     = null;
    private OrderProcessing theOrder     = null;


    /*
     * Construct the model of the Customer
     * @param mf The factory to create the connection objects
     */
    public CatalogueModel(MiddleFactory mf)
    {
        try                                          //
        {
            theStock = mf.makeStockReader();           // Database access
        } catch ( Exception e )
        {
            DEBUG.error("CustomerModel.constructor\n" +
                    "Database not created?\n%s\n", e.getMessage() );
        }
        theBasket = makeBasket();                    // Initial Basket
    }

    /**
     * return the Basket of products
     * @return the basket of products
     */
    public Basket getBasket()
    {
        return theBasket;
    }

    /**
     * Check if the product is in Stock
     * @param productName The product number

     */



    public void doCheckByName(String productName ) {
        String theAction = ""; //  set action to empty
        theBasket.clear();         //clear basket
        int amount = 1;            //  set amount to 1
        try {
            if (theStock.existsByName(productName))   //  check if product exists by name and returns boolean
            {
                ArrayList<Product> results = theStock.getDetailsByName(productName); //  store results of get details by name in arraylist
                theAction = "Showing Results for " + productName;
                if (!results.isEmpty()) {
                    for (Product product : results) {
                        if (product.getQuantity() >= 1) {//  check if product quantity is greater than or equal to 1
                            product.setQuantity(amount);// add 1  of item to basket
                            theBasket.add(product);// Add the produt to the basket
                        }
                    }
                } else {
                    theAction = "No Results for " + productName;//  if no results found
                }
            }else {
                    theAction = "No Results for " + productName;//  if no results found
            }
        } catch (StockException e) {
            DEBUG.error("CatalogueModel.doCheckByName()\n%s", e.getMessage());//  if error occurs
        }
        setChanged();//
        notifyObservers(theAction);//  notify observers
    }



    /**
     * ask for update of view callled at start
     */
    private void askForUpdate()
    {
        setChanged(); notifyObservers("START only"); // Notify
    }

    /**
     * Make a new Basket
     * @return an instance of a new Basket
     */
    protected Basket makeBasket()
    {
        return new Basket();
    }
}

