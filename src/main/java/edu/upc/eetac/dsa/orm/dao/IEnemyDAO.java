package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Enemy;

import java.util.List;

public interface IEnemyDAO {
    String addEnemy(Enemy enemy);
    Enemy getEnemyFromName(String name);
    int updateEnemy(Enemy enemy);
    int deleteEnemy(Enemy enemy);
    List<Enemy> getEnemies();
}

