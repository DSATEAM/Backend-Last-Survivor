package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Enemy;

import java.util.List;

public interface IEnemyDAO {
    String addEnemy(String name, String description, int power, int health);
    Enemy getEnemy(String enemyId);
    int updateEnemy(String enemyId, String name, String description, int power, int health);
    int deleteEnemy(String enemyId);
    List<Enemy> getEnemies();
}

