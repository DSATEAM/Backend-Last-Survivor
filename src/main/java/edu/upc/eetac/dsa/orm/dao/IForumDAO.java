package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Forum;
import edu.upc.eetac.dsa.orm.model.Message;

import java.util.List;

public interface IForumDAO {
    String addForum(Forum forum);
    String getId(String name);
    public Boolean existName(String name);
    Forum getForum(String id);
    int deleteForum(Forum forum);
    Forum updateForum(Forum forum);
    List<Forum> getForums();
}
