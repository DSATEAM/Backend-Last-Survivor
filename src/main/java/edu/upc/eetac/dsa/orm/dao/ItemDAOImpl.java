package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
import org.apache.log4j.Logger;

import java.util.List;

public class ItemDAOImpl implements IItemDAO{
    static final Logger logger = Logger.getLogger(ItemDAOImpl.class);
    @Override
    public String addItem(String parentId, String name, String type, String rarity, String description, int offense, int defense) {
        Item item = new Item(parentId,name,type,rarity,description,null,offense,defense);
        return saveItem(item);
    }

    private String saveItem(Item item) {
        Session session = null;
        String id = "";
        try {
            session = FactorySession.openSession();
            id = session.save(item);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return id;
    }

    @Override
    public String addItem(Item item) {
        return saveItem(item);
    }
    public int updateItemMaterials(Item item){
        Session session = null; int res;
        List<Material> listMaterialsItem;
        try {
            session = FactorySession.openSession();
            //Check if there are new materials if not don't add
            listMaterialsItem = item.getListMaterials();

            for(int i=0;i<listMaterialsItem.size();i++){
                session.update(listMaterialsItem.get(i));
            }
            res = 1;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    //Adds material which don't have ID to the DB and returns item with ID assigned
    //to materials
    public Item addItemMaterials(Item item) {
        Session session = null; String id = "";
        List<Material> listMaterialsItem = null;
        try {
            session = FactorySession.openSession();
            //Check if there are new materials if not don't add
            listMaterialsItem = item.getListMaterials();
            //Checking if new materials in the item
                //Items actually contains materials
            Material material = null;
            for(int i=0;i<listMaterialsItem.size();i++){
                //Check if the material has id, if not than save
                if(listMaterialsItem.get(i).getId().equals("")){
                   id =  session.save(listMaterialsItem.get(i));
                   material = listMaterialsItem.get(i);
                   material.setId(id);
                    listMaterialsItem.set(i,material);
                }
            }
            item.setListMaterials(listMaterialsItem);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }
    @Override
    public Item getItem(String itemId) {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.get(Item.class, itemId);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }

    @Override
    public int updateItem(Item item) {
        Session session = null;int res;
        try {
            session = FactorySession.openSession();
            //TODO MAKE UPDATE FUNCTION ALIVE
            session.update(item);
            res = 1;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }

    @Override
    public int deleteItem(String itemId) {
        Session session = null; int res;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item.setId(itemId);
            session.delete(item);
            res =1;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    @Override
    public int deleteMaterialItem(Material material) {
        Session session = null;int res=1;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            session.delete(material);

        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    @Override
    public List<Item> getListStandardItems() {
        Session session = null;
        List<Item> listMaterials = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            listMaterials =(List) session.findAll(Item.class);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return listMaterials;
    }

    @Override
    public List<Material> getListMaterialsByItemID(String itemId) {
        Session session = null;
        List<Material> listMaterials = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            listMaterials =(List) session.getList(Material.class, itemId);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return listMaterials;
    }
}
