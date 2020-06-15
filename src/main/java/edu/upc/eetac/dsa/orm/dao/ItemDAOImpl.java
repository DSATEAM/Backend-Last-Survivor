package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.RandomUtils;
import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ItemDAOImpl implements IItemDAO{
    static final Logger logger = Logger.getLogger(ItemDAOImpl.class);

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
        return buyItem(item);
    }
    @Override
    public String buyItem(Item item){
        //Generate Transaction for the Item with itemId and the ParentId(playerId)

        Session session = null;

        try {
            List<String> params = new LinkedList<>();
            String query = "INSERT INTO transaction(id,itemId,playerId) VALUES (?,?,?)";
            session = FactorySession.openSession();
            String transactionId = RandomUtils.generateID(32);
            params.add(transactionId);
            params.add(item.getId());
            params.add(item.getParentId());
            session.queryExecute(String.class,query,params);
            return transactionId;
            //Means we have list of Items now we must get the Standard Items
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            return null;
        }
        finally {
            if(session!=null)
            {  session.close();}
        }
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
            if(session!=null)
            {  session.close();}
        }

        return item;
    }

    @Override
    public int updateItem(Item item) {
        Session session = null; int res =0;
        try {
            session = FactorySession.openSession();
            res = session.update(item);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            if(session!=null)
            {  session.close();}
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
            if(session!=null)
            {  session.close();}
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
            String query = "SELECT * FROM Item";
            session = FactorySession.openSession();
            listItem = (List) session.queryExecuteGetObject(Item.class,query,params);
            //Means we have list of Items now we must get the Standard Items
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
            {  session.close();}
        }

        return listItem;
    }


}
