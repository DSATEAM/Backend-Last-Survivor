package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

import java.util.List;

public interface IPlayerDAO {
    String addPlayer(Player player);
    String getId(String username, String password);
    public Boolean existUsername(String username);
    Player getPlayer(String id);
    int deletePlayer(String id);
    Player updatePlayer(Player player);
    List<Player> getPlayers();
}
