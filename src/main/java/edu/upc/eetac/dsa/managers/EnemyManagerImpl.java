package edu.upc.eetac.dsa.managers;


import edu.upc.eetac.dsa.orm.dao.EnemyDAOImpl;
import edu.upc.eetac.dsa.orm.model.Enemy;

import org.apache.log4j.Logger;
import java.util.List;

public class EnemyManagerImpl implements EnemyManager{

    private static EnemyManager instance;
    EnemyDAOImpl enemyDAO = new EnemyDAOImpl();
    private static final Logger log = Logger.getLogger(EnemyManagerImpl.class);
    //Singleton implementation for the instance of the GameManager
    private EnemyManagerImpl(){
        //Singleton Private Constructor
    }
    public static EnemyManager getInstance() {
        if (instance == null) {
            instance = new EnemyManagerImpl();
        }
        return instance;
    }

    @Override
    public Enemy getEnemyFromName(Enemy enemy) {
        return enemyDAO.getEnemyFromName(enemy.getName());
    }

    @Override
    public String addEnemy(Enemy enemy) {
       return enemyDAO.addEnemy(enemy);
    }

    @Override
    public int updateEnemy(Enemy enemy) {
        return enemyDAO.updateEnemy(enemy);
    }

    @Override
    public int deleteEnemy(Enemy enemy) {
        return  enemyDAO.deleteEnemy(enemy);
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemyDAO.getEnemies();
    }
}
