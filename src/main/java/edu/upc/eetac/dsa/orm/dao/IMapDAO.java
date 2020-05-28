package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.orm.model.Map;
import java.util.List;

public interface IMapDAO {
    String addMap(Map map);
    Map getMap(String id);
    Map getMapFromName(String name);
    int updateMap(Map map);
    int deleteMap(Map map);
    List<Map> getMaps();

}
