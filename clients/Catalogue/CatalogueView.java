package clients.Catalogue;

import catalogue.Basket;
import catalogue.BetterBasket;
import clients.Picture;
import middle.MiddleFactory;
import middle.StockReader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */

public class CatalogueView implements Observer
{
    class Name                              // Names of buttons
    {
        public static final String CHECK  = "Check";
        public static final String CLEAR  = "Clear";
    }

    private static final int H = 500;       // Height of window pixels
    private static final int W = 600;       // Width  of window pixels

    private final JLabel      theAction  = new JLabel();
    private final JTextField  theInput   = new JTextField();
    private final JTextArea   theOutput  = new JTextArea();
    private final JScrollPane theSP      = new JScrollPane();
    private final JButton     theBtCheck = new JButton( Name.CHECK );
    private StockReader theStock   = null;
    private CatalogueController cont= null;

    Color darkGreen = new Color(10, 128, 31);  // Dark green for check button

    /**
     * Construct the view
     * @param rpc   Window in which to construct
     * @param mf    Factor to deliver order and stock objects
     * @param x     x-cordinate of position of window on screen
     * @param y     y-cordinate of position of window on screen
     */

    public CatalogueView( RootPaneContainer rpc, MiddleFactory mf, int x, int y )
    {
        try                                             //
        {
            theStock  = mf.makeStockReader();             // Database Access
        } catch ( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
        Container cp         = rpc.getContentPane();    // Content Pane
        Container rootWindow = (Container) rpc;         // Root Window
        cp.setLayout(null);                             // No layout manager
        rootWindow.setSize( W, H );                     // Size of Window
        rootWindow.setLocation( x, y );
        cp.setBackground( Color.white );                // White back ground

        Font f = new Font("Monospaced",Font.PLAIN,25);  // Font f is

        theBtCheck.setBounds( 380, 50, 80, 40 );    // Check button
        theBtCheck.setBackground(darkGreen);      // darkGreen background
        theBtCheck.setForeground(Color.white);    // White text
        theBtCheck.addActionListener(                   // Call back code
                e -> cont.doCheckByName( theInput.getText() ) );
        cp.add( theBtCheck );                           //  Add to canvas


        theAction.setBounds( 110, 25 , 360, 20 );       // Message area
        theAction.setText( "Please Enter The Product Name Or Press Check To View All" );                        //  Blank
        cp.add( theAction );                            //  Add to canvas

        theInput.setBounds( 110, 50, 270, 40 );         // Product no area
        theInput.setText("");                           // Blank
        theInput.setToolTipText("Enter Product Name Here"); // Hint
        cp.add( theInput );                             //  Add to canvas

        theSP.setBounds( 1, 100, 585, 360 );// Scrolling pane
        theSP.setBorder( BorderFactory.createLineBorder( Color.black,0 ) );//Hide the border
        theOutput.setEditable( false );                 // Read only
        theOutput.setText( "" );                        //  Blank
        theOutput.setFont( f );//  Uses font
        cp.add( theSP );                                //  Add to canvas
        theSP.getViewport().add( theOutput );           //  In TextArea


        rootWindow.setVisible( true );                  // Make visible);
        theInput.requestFocus();                        // Focus is here
    }

    /**
     * The controller object, used so that an interaction can be passed to the controller
     * @param c   The controller
     */

    public void setController( CatalogueController c )
    {
        cont = c;
    }

    /**
     * Update the view
     * @param modelC   The observed model
     * @param arg      Specific args
     */

    public void update( Observable modelC, Object arg )
    {
        CatalogueModel model  = (CatalogueModel) modelC;
        String        message = (String) arg;
        theAction.setText( message );
        theOutput.setToolTipText("Results will show here");
        theOutput.setText(model.getBasket().getDetailsNoTotal());
        theInput.requestFocus();               // Focus is here
    }

}
