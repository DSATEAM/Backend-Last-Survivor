package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface StoreManager {
    public int checkCredit(Player player, List<Item> items);
    public Player addItem(Player player, List<Item> items);
}
