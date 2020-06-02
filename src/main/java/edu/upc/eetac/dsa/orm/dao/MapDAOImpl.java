package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Map;
import edu.upc.eetac.dsa.orm.model.Player;

import java.util.LinkedList;
import java.util.List;

public class MapDAOImpl implements IMapDAO{

    public String addMap(Map map) {

        Session session = null;
        String id = "";
        try {
            session = FactorySession.openSession();
           id =  session.save(map);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return id;
    }

    public Map getMap(String id){

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

    @Override
    public Map getMapFromName(String name) {
        Session session = null;
        Map map = null;
        try {
            String query = "SELECT * FROM Map WHERE name = ?";
            session = FactorySession.openSession();
            List<String> params = new LinkedList<>();
            List<Object> result;
            params.add(name);
            result = (List)session.queryExecuteGetObject(Map.class, query,params);
            if(result.size()!=0) return (Map) result.get(0);
            else return null;
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return map;
    }

    public int updateMap(Map map){
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


    public int deleteMap(Map map){

        Session session = null;
        int res=0;
        try {
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

    public List<Map> getMaps(){
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



