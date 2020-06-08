package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Item;
import java.util.List;

public interface IItemDAO {
    String addItem(Item item);
    String addItem(String parentId, String name, String type, String rarity, String description, int offense, int defense,float hitRange, float attackCooldown);
    Item getItem(String itemId);
    int updateItem(Item item);
    int deleteItem(String itemId);
    List<Item> getListStandardItems();
}
