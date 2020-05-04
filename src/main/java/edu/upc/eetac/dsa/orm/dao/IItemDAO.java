package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;

import java.util.List;

public interface IItemDAO {
    String addItem(Item item);
    String addItem(String parentID, String name, String type, String rarity, String description, int offense, int defense);
    Item addItemMaterials(Item item);
    Item getItem(String itemID);
    int updateItem(Item item);
    int updateItemMaterials(Item item);
    int deleteItem(String itemID);
    int deleteMaterialItem(Material material);
    List<Item> getListStandardItems();//getsItems with Zero Material as this are standard items
    List <Material> getListMaterialsByItemID(String itemId);
}
