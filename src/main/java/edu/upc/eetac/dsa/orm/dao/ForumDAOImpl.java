package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Forum;
import edu.upc.eetac.dsa.orm.model.Message;


import java.util.LinkedList;
import java.util.List;

public class ForumDAOImpl implements IForumDAO{

    public String addForum(Forum forum) {
        Session session = null;

        String id=null;
        try {
            session = FactorySession.openSession();
            id=session.save(forum);
        }
        catch (Exception e) {
            // LOG
            return null;
        }
        finally {
            session.close();
        }
        return id;
    }

    public Boolean existName(String name){
        Session session = null;
        int users; List ids;
        try {
            session = FactorySession.openSession();
            String query = "SELECT * FROM forum WHERE name = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(name);
            ids = (List) session.queryExecute(String.class, query,paramsList);
            return !ids.isEmpty();
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return false;
    }

    public String getId(String name){
        Session session = null;
        String forumId = null; List ids;
        try {
            session = FactorySession.openSession();
            String query = "SELECT id FROM forum WHERE name = ?"; List<String> paramsList = new LinkedList<>();
            paramsList.add(name);
            ids = (List) session.queryExecute(String.class, query,paramsList);
            if(ids.isEmpty()) return null;
            for(Object id : ids){
                forumId = (String) id;
            }
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return forumId;
    }
    public Forum getForum(String id){
        Session session = null;
        Forum forum = null;
        List<Message> listItems = null;
        try {
            session = FactorySession.openSession();
            forum = (Forum) session.get(Forum.class, id);
            //Now that we have the player we must add the items and materials of the player
            List<Message> messages =(List) session.getList(Message.class, forum.getParentId());
            forum.setListMessages(messages);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return forum;
    }
    public int deleteForum(Forum forum){
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(forum);
            res = 1;
        }
        catch (Exception e) {
            // LOG
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }
    public Forum updateForum(Forum forum) {
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(forum);
            res = 1;

        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
            forum = null;
        }
        finally {
            session.close();
        }
        return forum;
    }

    public List<Forum> getForums() {
        Session session = null;
        List forumList=null;
        try {
            session = FactorySession.openSession();
            forumList = (List) session.findAll(Forum.class);
            System.out.println("La lista es " + forumList);
            

        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return forumList;
    }
}
