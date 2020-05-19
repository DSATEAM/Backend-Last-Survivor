package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingManagerImpl implements RankingManager{
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    private static RankingManager instance;
    @Override
    public List<Player> getPlayers() {
        List<Player> list = playerDAO.getPlayers();
        Collections.sort(list);
        return list;
    }
    public static RankingManager getInstance() {
        if (instance == null) {
            instance = new RankingManagerImpl();
        }
        return instance;
    }

}
