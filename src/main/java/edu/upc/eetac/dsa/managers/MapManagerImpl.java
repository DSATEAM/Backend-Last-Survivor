package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import org.apache.log4j.Logger;

public class MapManagerImpl implements MapManager{
    private static MapManager instance;
    MapManagerImpl playerDAO = new MapManagerImpl();
    private static final Logger log = Logger.getLogger(MapManagerImpl.class);
    //Singleton implementation for the instance of the GameManager
    private MapManagerImpl(){
    }
    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManagerImpl();
        }
        return instance;
    }
    //Code from here down below
}
