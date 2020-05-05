package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Player;
import org.apache.log4j.Logger;

public class PlayerManagerImpl implements PlayerManager {
    private static PlayerManager instance;
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
    public int signUp(String userName,String Password) {
        //Save this User with this name and password
            //If unique add -->result 1
            //Exists --> result -1
        return -1;
    }

    @Override
    public String signIn(String userName,String Password) {
        //If correct return id, else empty String(Not Null)!
        return null;
    }

    @Override
    public int signOut(String ID) {
        //Remove the Player Local Instance from the List<Players>
        //Also Update
        return 0;
    }

    @Override
    public Player getPlayer(String ID) {
        return null;
    }

    @Override
    public int updatePlayer(Player player) {
        //1 Good -1 Error
        return -1;
    }

    @Override
    public int deletePlayer(String ID) {
        return 0;
    }
}
