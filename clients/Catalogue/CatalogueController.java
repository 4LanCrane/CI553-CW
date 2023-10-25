package clients.Catalogue;

/**
 * The Customer Controller
 * @author M A Smith (c) June 2014
 */

public class CatalogueController
{
    private CatalogueModel model = null;
    private CatalogueView  view  = null;

    /**
     * Constructor
     * @param model The model
     * @param view  The view from which the interaction came
     */
    public CatalogueController( CatalogueModel model, CatalogueView view )
    {
        this.view  = view;
        this.model = model;
    }

    /**
     * Check interaction from view
     * @param pn The product number to be checked
     */


    public void doCheckByName( String pn )
    {
        model.doCheckByName(pn);
    }

    /**
     * Clear interaction from view
     */
    public void doClear()
    {
        model.doClear();
    }


}

