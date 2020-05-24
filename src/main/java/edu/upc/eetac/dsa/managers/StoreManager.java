package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface StoreManager {
    public int checkPurchase(Item items);
    public void addItem(Item items);
    public List<Item> getItems();
}
