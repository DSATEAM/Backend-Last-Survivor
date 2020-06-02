package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Map;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public interface MapManager {
    //Methods Interface to implement
    Map getMapFromName(Map map);
    Map getMap(Map map);
    String addMap(Map map);
    List<Map> getMaps();
    int updateMap(Map map);
    int deleteMap(Map map);
}
