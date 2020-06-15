package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingManagerImpl implements RankingManager{
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    private static RankingManager instance;
    @Override
    public List<RankingDTO> getPlayers() {
        List<Player> list = playerDAO.getPlayers();
        List<RankingDTO> rankingList=new ArrayList<>();
        for (Player p : list) {
            RankingDTO pos = new RankingDTO();
            pos.setExperience(p.getExperience());
            pos.setCredits(p.getCredits());
            pos.setGamesPlayed(p.getGamesPlayed());
            pos.setKills(p.getKills());
            pos.setUsername(p.getUsername());
            pos.setAvatar(p.getAvatar());
            pos.setMaxFloor(p.getMaxFloor());
            rankingList.add(pos);
        }
        Collections.sort(rankingList);
        return rankingList;
    }
    public static RankingManager getInstance() {
        if (instance == null) {
            instance = new RankingManagerImpl();
        }
        return instance;
    }

}
