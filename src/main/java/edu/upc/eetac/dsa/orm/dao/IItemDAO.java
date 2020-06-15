package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Item;
import java.util.List;

public interface IItemDAO {
    void addItem(Item item);
    void buyItem(Item item);
    Item getItem(String itemId);
    int updateItem(Item item);
    int deleteItem(String itemId);
    List<Item> getListStandardItems();
}
