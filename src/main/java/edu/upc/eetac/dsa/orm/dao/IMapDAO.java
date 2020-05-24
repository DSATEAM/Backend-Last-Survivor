package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.orm.model.Map;
import java.util.List;

public interface IMapDAO {
    String addMap(String name,int level, String type1MapGrid, String type2PlayerPosition, String type2EntityPositions);
    Map getMap(String id);
    int updateMap(Map map);
    int deleteMap(String id);
    List<Map> getMaps();

}
