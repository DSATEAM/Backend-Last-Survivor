package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Enemy;
import edu.upc.eetac.dsa.orm.model.Map;

import java.util.LinkedList;
import java.util.List;

public class EnemyDAOImpl implements IEnemyDAO{

    public String addEnemy(Enemy enemy) {
        Session session = null;
        String id=null;
        try {
            session = FactorySession.openSession();
            id=session.save(enemy);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return id;
    }

    @Override
    public Enemy getEnemyFromName(String name) {
        Session session = null;
        Enemy enemy = null;
        try {
            String query = "SELECT * FROM Enemy WHERE name = ?";
            session = FactorySession.openSession();
            List<String> params = new LinkedList<>();
            List<Object> result;
            params.add(name);
            result = (List)session.queryExecuteGetObject(Enemy.class, query,params);
            if(result.size()!=0) return (Enemy) result.get(0);
            else return null;
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return enemy;
    }

    public int updateEnemy(Enemy enemy) {
        int res = 0;
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(enemy);
            res=1;
        }
        catch (Exception e) {
            // LOG
            res = -1;
        }
        finally {
            session.close();
        }
        return res;
    }


    public int deleteEnemy(Enemy enemy) {
        Session session = null;
        int res = 0;
        try {
            session = FactorySession.openSession();
            session.delete(enemy);
            res=1;
        }
        catch (Exception e) {
            // LOG
            res = -1;
        }
        finally {
            session.close();
        }

        return res;

    }


    public List<Enemy> getEnemies() {
        Session session = null;
        List<Enemy> enemyList=null;
        try {
            session = FactorySession.openSession();
            enemyList = session.findAll(Enemy.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return enemyList;
    }

}
