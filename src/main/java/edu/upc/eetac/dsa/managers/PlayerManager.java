package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;

public interface PlayerManager {

    String signUp(Player player);
    String signIn(Player player);
    Player getPlayer(String ID);
    Player updatePlayer(Player player);
    int deletePlayer(String playerId);
}
