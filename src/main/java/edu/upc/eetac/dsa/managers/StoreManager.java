package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface StoreManager {
    int checkPurchase(Item item);
    void addItem(Item item);
    List<Item> getItems();
}
