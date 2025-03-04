package clients.cashier;

import catalogue.Basket;
import clients.customer.CustomerView;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockReadWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


/**
 * View of the model
 * @author  M A Smith (c) June 2014  
 */
public class CashierView implements Observer
{
  private static final int H = 300;       // Height of window pixels
  private static final int W = 470;       // Width  of window pixels

  private static final String CHECK  = "Check";
  private static final String ADD    = "ADD";
  private static final String BUY = "BUY";

  private static final String CLEAR = "Clear";

  private final JLabel      theAction  = new JLabel();
  private final JTextField  theInput   = new JTextField();
  private final JTextArea   theOutput  = new JTextArea();
  private final JScrollPane theSP      = new JScrollPane();
  private final JButton     theBtCheck = new JButton( CHECK );
  private final JButton     theBtAdd   = new JButton( ADD );
  private final JButton     theBtClear = new JButton( CLEAR);

  private final JButton     theBtBought= new JButton( BUY );

  private final JLabel      theAmountLabel  = new JLabel("Quantity"); //label for input
  private final JSpinner theAmount = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));

  private StockReadWriter theStock     = null;
  private OrderProcessing theOrder     = null;
  private CashierController cont       = null;
  Color darkGreen = new Color(10, 128, 31);
  Color darkRed = new Color(157, 45, 15);

  Color darkOrange = new Color(178, 99, 13);

  Color darkYellow = new Color(162, 162, 13);
  
  /**
   * Construct the view
   * @param rpc   Window in which to construct
   * @param mf    Factor to deliver order and stock objects
   * @param x     x-coordinate of position of window on screen 
   * @param y     y-coordinate of position of window on screen  
   */
          
  public CashierView(  RootPaneContainer rpc,  MiddleFactory mf, int x, int y  )
  {
    try                                           // 
    {      
      theStock = mf.makeStockReadWriter();        // Database access
      theOrder = mf.makeOrderProcessing();        // Process order
    } catch ( Exception e )
    {
      System.out.println("Exception: " + e.getMessage() );
    }
    Container cp         = rpc.getContentPane();    // Content Pane
    Container rootWindow = (Container) rpc;         // Root Window
    cp.setLayout(null);                             // No layout manager
    rootWindow.setSize( W, H );                     // Size of Window
    rootWindow.setLocation( x, y );

    Font f = new Font("Monospaced",Font.PLAIN,12);  // Font f is

    theBtCheck.setBounds( 16, 25+60*0, 80, 40 );    // Check Button
    theBtCheck.setBackground( darkGreen );          //  Background color
    theBtCheck.setForeground( Color.white );        //  Text colour
    theBtCheck.addActionListener(                   // Call back code
      e -> cont.doCheck( theInput.getText(),Integer.parseInt(String.valueOf(theAmount.getValue()))));
    cp.add( theBtCheck );                           //  Add to canvas

    theBtAdd.setBounds( 16, 25+60*1, 80, 40 );      // Buy button
    theBtAdd.setBackground( darkOrange );           //  Background color
    theBtAdd.setForeground( Color.white );          //  Text colour
    theBtAdd.addActionListener(                     // Call back code
      e -> cont.doBuy() );
    cp.add( theBtAdd );                             //  Add to canvas

    theBtClear.setBounds( 16, 25+60*2, 80, 40 );    // Clear Button
    theBtClear.setBackground( darkRed );            //  Background color
    theBtClear.setForeground( Color.white );        //  Text colour
    theBtClear.addActionListener(                   // Call back code
      e -> cont.doClear() );
    cp.add( theBtClear );                           //  Add to canvas

    theBtBought.setBounds( 16, 25+60*3, 80, 40 );   // Clear Button
    theBtBought.setBackground( darkYellow );        //  Background color
    theBtBought.setForeground( Color.white );       //  Text colour
    theBtBought.addActionListener(                  // Call back code
      e -> cont.doBought() );
    cp.add( theBtBought );                          //  Add to canvas

    theAction.setBounds( 110, 25 , 270, 20 );       // Message area
    theAction.setText( "" );                        // Blank
    cp.add( theAction );                            //  Add to canvas

    theInput.setBounds( 110, 50, 270, 40 );         // Input Area
    theInput.setText("");                           // Blank
    cp.add( theInput );

    theAmountLabel.setBounds( 390, 25 , 270, 20 );// label Prompt for product no
    cp.add( theAmountLabel );// add amount label to canvas
    theAmount.setBounds(380,50,60,40);// input spinner for amount
    cp.add(theAmount);// add amount spinner to canvas

    theSP.setBounds( 110, 100, 270, 160 );          // Scrolling pane
    theOutput.setText( "" );                        //  Blank
    theOutput.setFont( f );                         //  Uses font  
    cp.add( theSP );                                //  Add to canvas
    theSP.getViewport().add( theOutput );           //  In TextArea
    rootWindow.setVisible( true );                  // Make visible
    theInput.requestFocus();                        // Focus is here
  }

  /**
   * The controller object, used so that an interaction can be passed to the controller
   * @param c   The controller
   */

  public void setController( CashierController c )
  {
    cont = c;
  }

  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
  @Override
  public void update( Observable modelC, Object arg )
  {
    CashierModel model  = (CashierModel) modelC;
    String      message = (String) arg;
    theAction.setText( message );
    Basket basket = model.getBasket();
    if ( basket == null )
      theOutput.setText( "Customers order" );
    else
      theOutput.setText( basket.getDetails() );
    
    theInput.requestFocus();               // Focus is here
  }

}
