package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface PlayerManager {

    String signUp(String userName, String Password);
    String signIn(String userName, String Password);
    int signOut(String ID);
    Player getPlayer(String ID);
    int updatePlayer(Player player);
    int deletePlayer(String ID);
}
