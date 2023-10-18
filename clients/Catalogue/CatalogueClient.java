package clients.Catalogue;

import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import middle.MiddleFactory;
import middle.Names;
import middle.RemoteMiddleFactory;

import javax.swing.*;

/**
 * The standalone Customer Client
 * @author  Mike Smith University of Brighton
 * @version 2.0
 */
public class CatalogueClient
{
    public static void main (String args[])
    {
        String stockURL = args.length < 1         // URL of stock R
                ? Names.STOCK_R           //  default  location
                : args[0];                //  supplied location

        RemoteMiddleFactory mrf = new RemoteMiddleFactory();
        mrf.setStockRInfo( stockURL );
        displayGUI(mrf);                          // Create GUI
    }

    private static void displayGUI(MiddleFactory mf)
    {
        JFrame  window = new JFrame();
        window.setTitle( "Customer Client (MVC RMI)" );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        CatalogueModel model = new CatalogueModel(mf);
        CatalogueView  view  = new CatalogueView( window, mf, 0, 0 );
       CatalogueController cont  = new CatalogueController( model, view );
        view.setController( cont );

        model.addObserver( view );       // Add observer to the model
        window.setVisible(true);         // Display Scree
    }
}
