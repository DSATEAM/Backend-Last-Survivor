package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Enemy;



import java.util.List;

public interface EnemyManager {
    Enemy getEnemyFromName(Enemy enemy);
    String addEnemy(Enemy enemy);
    int updateEnemy(Enemy enemy);
    int deleteEnemy(Enemy enemy);
    List<Enemy> getEnemies();

}
