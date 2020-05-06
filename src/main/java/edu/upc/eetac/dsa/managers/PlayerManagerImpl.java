package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import org.apache.log4j.Logger;

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
    public int signUp(String username,String password) {
        String playerID = playerDAO.getId(username,password);
        if(playerID==null) {
           playerID =  playerDAO.addPlayer(username,password,0,0,0,0,0);
           return 1;
        }else{
           return -1;
        }
    }

    @Override
    public String signIn(String username,String password) {
        //If correct return id, else empty String(Not Null)
        String playerID = playerDAO.getId(username,password);
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
    public int updatePlayer(Player player) {
        return playerDAO.updatePlayer(player);
    }

    @Override
    public int deletePlayer(String playerID) {
       return playerDAO.deletePlayer(playerID);
    }
}
