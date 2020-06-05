package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Forum;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

import java.util.List;

public interface ForumManager {
    String createForum(Forum forum);
    Forum getForum(String id);
    Forum updateForum(Forum forum);
    int deleteForum(Forum forum);
    List<Forum> getForums();
}
