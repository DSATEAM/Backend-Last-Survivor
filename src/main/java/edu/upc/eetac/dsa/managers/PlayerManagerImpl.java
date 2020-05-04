package edu.upc.eetac.dsa.managers;

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

}
