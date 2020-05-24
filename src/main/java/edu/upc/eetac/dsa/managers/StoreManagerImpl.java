package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public class StoreManagerImpl implements StoreManager {
    private static StoreManager instance;
    private static final Logger log = Logger.getLogger(StoreManagerImpl.class);
    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManagerImpl();
        }
        return instance;
    }

    @Override
    public int checkPurchase(Item items) {
        //Now we can Continue checking if the player contains any credit for the items
            //If items already exist than ignore and go to next
                //If all of the items same than return 0
        return -1;
    }

    @Override
    public Item addItem(Item items) {
        //Now we can add to the player items and ignore the ones which already exist
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

}
