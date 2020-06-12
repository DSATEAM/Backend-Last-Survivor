package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.ForumDAOImpl;
import edu.upc.eetac.dsa.orm.model.Forum;
import org.apache.log4j.Logger;

import java.util.List;

public class ForumManagerImpl implements ForumManager{
    private static ForumManager instance;
    ForumDAOImpl forumDAO = new ForumDAOImpl();
    private static final Logger log = Logger.getLogger(PlayerManagerImpl.class);

    public static ForumManager getInstance() {
        if (instance == null) {
            instance = new ForumManagerImpl();
        }
        return instance;
    }

    @Override
    public String createForum(Forum forum) {
        if(forumDAO.existName(forum.getName())) {
            return null;
        }else{
            return forumDAO.addForum(forum);
        }
    }

    @Override
    public Forum getForum(String id) {
        //Returns Forum with Messageslist included!
        return forumDAO.getForum(id);
    }

    @Override
    public Forum updateForum(Forum forum) { return forumDAO.updateForum(forum); }

    @Override
    public int deleteForum(Forum forum) { return forumDAO.deleteForum(forum); }

    @Override
    public List<Forum> getForums() { return forumDAO.getForums(); }
}
