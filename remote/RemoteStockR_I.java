package remote;

import catalogue.Product;
import middle.StockException;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Defines the RMI interface for read access to the stock object.
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */

public interface RemoteStockR_I
       extends Remote
{
  boolean   exists(String number)
            throws RemoteException, StockException;

    boolean   existsByName(String name)
            throws RemoteException, StockException;
  Product   getDetails(String number)
            throws RemoteException, StockException;

    ArrayList<Product> getDetailsByName(String name)
            throws RemoteException, StockException;

  ImageIcon getImage(String number)
            throws RemoteException, StockException;


}

