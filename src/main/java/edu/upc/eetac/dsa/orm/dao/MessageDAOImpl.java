package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageDAOImpl implements IMessageDAO {
    @Override
    public String addMessage(Message message) {
        return saveMessage(message);
    }

    private String saveMessage(Message message) {
        Session session = null;
        String id = "";
        try {
            session = FactorySession.openSession();
            id = session.save(message);
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
    public Message getMessage(String id) {
        Session session = null;
        Message message = null;
        try {
            session = FactorySession.openSession();
            message = (Message) session.get(Message.class, id);
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return message;
    }

    @Override
    public int deleteMessage(Message message) {
        Session session = null; int res;
        try {
            session = FactorySession.openSession();
            session.delete(message);
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
    public List<Message> getListMessages(String parentId) {
        Session session = null;
        List<Message> listMessages = null;
        Item item = null;
        try {
            List<String> params = new LinkedList<>();
            params.add(parentId);
            String query = "SELECT * FROM Message WHERE parentId=?";
            session = FactorySession.openSession();
            listMessages = (List) session.queryExecuteGetObject(Message.class,query,params);
            //Means we have list of Items now we must get the Standard Items
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return listMessages;
    }
}
