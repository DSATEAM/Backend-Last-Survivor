package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface IPlayerDAO {
    String addPlayer(String username, String password, int gamesPlayed, int kills, int deaths, int experience, int wins);
    String getId(String username, String password);
    Player getPlayer(String id);
    int deletePlayer(String id);
    int updatePlayer(Player player);
    List<Player> getPlayers();
}
