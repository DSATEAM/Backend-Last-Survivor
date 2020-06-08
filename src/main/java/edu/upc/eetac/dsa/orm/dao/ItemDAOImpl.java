package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ItemDAOImpl implements IItemDAO{
    static final Logger logger = Logger.getLogger(ItemDAOImpl.class);
    @Override
    public String addItem(String parentId, String name, String type, String rarity, String description, int offense, int defense) {
        Item item = new Item(parentId,name,type,rarity,description,50,offense,defense);
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
        Session session = null;int res =0;
        try {
            session = FactorySession.openSession();
            //TODO MAKE UPDATE FUNCTION ALIVE
            res = session.update(item);
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
        Session session = null; int res = 0;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item.setId(itemId);
            res = session.delete(item);
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
        List<Item> listItem = null;
        Item item = null;
        try {
            List<String> params = new LinkedList<>();
            params.add("#standard");
            String query = "SELECT * FROM Item WHERE parentId=?";
            session = FactorySession.openSession();
            listItem = (List) session.queryExecuteGetObject(Item.class,query,params);
            //Means we have list of Items now we must get the Standard Items
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return listItem;
    }


}
