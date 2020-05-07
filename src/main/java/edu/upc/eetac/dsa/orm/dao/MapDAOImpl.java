package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Map;

import java.util.List;

public class MapDAOImpl implements IMapDAO {

    public String addMap(String id, int sizeX, int sizeY, String designGrid) {

        Session session = null;
        String mapID = "";//o null
        try {
            session = FactorySession.openSession();
            Map map1 = new Map(id,sizeX,sizeY,designGrid);
            session.save(map1);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return mapID;
    }

    public Map getMap(String mapId) {

        Session session = null;
        Map map1 = null;
        try {
            session = FactorySession.openSession();
            map1 = (Map)session.get(Map.class, mapId);

        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return map1;
    }

    public int updateMap(String mapId, int sizeX, int sizeY, String designGrid) {
        Map map1 = this.getMap(mapId);
        map1.setSizeX(sizeX);
        map1.setSizeY(sizeY);
        map1.setDesignGrid(designGrid);
        Session session=null;
        int res=0;
        try {
            session = FactorySession.openSession();
            session.update(map1);
            res= 1;
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return res;
    }


    public int deleteMap(String mapId, int sizeX, int sizeY, String designGrid) {
        Map map1 = this.getMap(mapId);
        Session session = null;
        int res=0;
        try {
            session = FactorySession.openSession();
            session.delete(map1);
            res=1;
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
       return  res;

    }

    public List<Map> getMaps() {
        Session session = null;
        List<Map> mapList=null;
        try {
            session = FactorySession.openSession();
            mapList = session.findAll(Map.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return mapList;
    }
}



