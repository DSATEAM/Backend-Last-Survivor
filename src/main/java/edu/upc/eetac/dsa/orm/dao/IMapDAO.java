package edu.upc.eetac.dsa.orm.dao;
import edu.upc.eetac.dsa.orm.model.Map;
import java.util.List;

public interface IMapDAO {
    String addMap(String ID, int sizeX,int sizeY, String designGrid);
    Map getMap(String mapID);
    void updateMap(String mapID,int sizeX,int sizeY, String designGrid);
    void deleteMap(String mapID,int sizeX,int sizeY, String designGrid);//con el ID seria suficiente
    List<Map> getMaps();

}
