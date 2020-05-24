package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Map;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.List;

public class MapDAOImpl implements IMapDAO {

    public String addMap( String name,int level, String type1MapGrid, String type2PlayerPosition, String type2EntityPositions) {

        Session session = null;
        String id = "";
        try {
            session = FactorySession.openSession();
            Map map = new Map(name,id,level,type1MapGrid,type2PlayerPosition,type2EntityPositions);
            session.save(map);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return id;
    }

    public Map getMap(String id) {

        Session session = null;
        Map map = null;
        try {
            session = FactorySession.openSession();
            map = (Map)session.get(Map.class, id);

        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return map;
    }

    public int updateMap(Map map) {
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(map);
            res = 1;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }


    public int deleteMap(String id) {

        Session session = null;
        int res=0;
        try {
            Map map = this.getMap(id);
            session = FactorySession.openSession();
            session.delete(map);
            res=1;
        }
        catch (Exception e) {
            // LOG
            res = -1;
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
            mapList =(List) session.findAll(Map.class);
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



