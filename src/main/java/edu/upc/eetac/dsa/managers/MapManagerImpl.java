package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.dao.MapDAOImpl;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Map;
import org.apache.log4j.Logger;

import java.util.List;

public class MapManagerImpl implements MapManager{
    private static MapManager instance;
    MapDAOImpl mapDAO = new MapDAOImpl();
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

    @Override
    public Map getMapFromName(Map map) {
        return mapDAO.getMapFromName(map.getName());

    }

    @Override
    public Map getMap(Map map) {
        return mapDAO.getMap(map.getId());
    }

    @Override
    public String addMap( Map map) {
        return mapDAO.addMap(map);
    }

    @Override
    public List<Map> getMaps() {
        return mapDAO.getMaps();
    }

    @Override
    public int updateMap(Map map) {
        return mapDAO.updateMap(map);
    }

    @Override
    public int deleteMap(Map map) {
        return mapDAO.deleteMap(map);
    }
    //Code from here down below
}
