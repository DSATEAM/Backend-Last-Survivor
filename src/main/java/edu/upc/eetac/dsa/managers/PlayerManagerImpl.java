package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import org.apache.log4j.Logger;

import java.util.List;

public class PlayerManagerImpl implements PlayerManager {
    private static PlayerManager instance;
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    private static final Logger log = Logger.getLogger(PlayerManagerImpl.class);
    //Singleton implementation for the instance of the GameManager
    private PlayerManagerImpl(){
        //Singleton Private Constructor
        //this.mapUser = new HashMap<>();
        //this.listGameObjects = new LinkedList<>();
    }
    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManagerImpl();
        }
        return instance;
    }

    @Override
    public String signUp(Player player) {
        if(playerDAO.existUsername(player.getUsername())) {
            return null;
        }else{
            String playerID = playerDAO.addPlayer(player);
            return playerID;
        }
    }

    @Override
    public String signIn(Player player) {
        //If correct return id, else empty String(Not Null)
        String playerID = playerDAO.getId(player.getUsername(),player.getPassword());
        return playerID;
    }

    @Override
    public int signOut(String playerID) {
        //Remove the Player Local Instance from the List<Players> ?? MAYBE
        //WHATS THE USE??
        return 0;
    }

    @Override
    public Player getPlayer(String playerID) {
        //Returns Player with Items and Materials list included!
        return playerDAO.getPlayer(playerID);
    }

    @Override
    public Player updatePlayer(Player player) {
        return playerDAO.updatePlayer(player);
    }

    @Override
    public int deletePlayer(String playerID) {
       return playerDAO.deletePlayer(playerID);
    }
}
