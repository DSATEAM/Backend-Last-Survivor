package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.orm.model.Map;
import java.util.List;

public interface IMapDAO {
    String addMap(String Id, int sizeX,int sizeY, String designGrid);
    Map getMap(String mapID);
    void updateMap(String mapId,int sizeX,int sizeY, String designGrid);
    void deleteMap(String mapId,int sizeX,int sizeY, String designGrid);//con el ID seria suficiente
    List<Map> getMaps();

}
