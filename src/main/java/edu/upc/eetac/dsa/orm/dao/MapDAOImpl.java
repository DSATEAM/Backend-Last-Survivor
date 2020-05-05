package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Employee;
import edu.upc.eetac.dsa.orm.model.Map;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public class MapDAOImpl implements IMapDAO {

    public String addMap(String ID, int sizeX, int sizeY, String designGrid) {

        Session session = null;
        String mapID = "";//o null
        try {
            session = FactorySession.openSession();
            Map map1 = new Map(ID,sizeX,sizeY,designGrid);
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

    public Map getMap(String mapID) {

        Session session = null;
        Map map1 = null;
        try {
            session = FactorySession.openSession();
            map1 = (Map)session.get(Map.class, mapID);

        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return map1;
    }

    public void updateMap(String mapID, int sizeX, int sizeY, String designGrid) {
        Map map1 = this.getMap(mapID);
        map1.setSizeX(sizeX);
        map1.setSizeY(sizeY);
        map1.setDesignGrid(designGrid);
        Session session=null;
        try {
            session = FactorySession.openSession();
            session.update(map1);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }


    public void deleteMap(String mapID, int sizeX, int sizeY, String designGrid) {
        Map map1 = this.getMap(mapID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(map1);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

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



