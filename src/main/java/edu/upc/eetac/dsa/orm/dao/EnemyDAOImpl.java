package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.FactorySession;
import edu.upc.eetac.dsa.orm.Session;
import edu.upc.eetac.dsa.orm.model.Enemy;

import java.util.List;

public class EnemyDAOImpl implements IEnemyDAO{

    public String addEnemy(String name, String description, int power, int health) {
        Session session = null;
        String id=null;
        try {
            session = FactorySession.openSession();
            Enemy enemy = new Enemy(name, description, power, health);
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


    public Enemy getEnemy(String enemyID) {
        Session session = null;
        Enemy enemy = null;
        try {
            session = FactorySession.openSession();
            enemy = (Enemy)session.get(Enemy.class, enemyID);

        }
        catch (Exception e) {
            // LOG
        }

        finally {
            session.close();
        }

        return enemy;
    }


    public int updateEnemy(String enemyID, String name,String description, int power, int health) {
        Enemy enemy = this.getEnemy(enemyID);
        enemy.setName(name);
        enemy.setDescription(description);
        enemy.setPower(power);
        enemy.setHealth(health);
        int res = 0;

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(enemy);
            res=1;
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return res;
    }


    public int deleteEnemy(String enemyID) {
        Enemy enemy = this.getEnemy(enemyID);
        Session session = null;
        int res = 0;
        try {
            session = FactorySession.openSession();
            session.delete(enemy);
            res=1;
        }
        catch (Exception e) {
            // LOG
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
