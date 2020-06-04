package edu.upc.eetac.dsa.managers;

import edu.upc.eetac.dsa.orm.model.Enemy;



import java.util.List;

public interface EnemyManager {
    public Enemy getEnemyFromName(Enemy enemy);
    public String addEnemy(Enemy enemy);
    int updateEnemy(Enemy enemy);
    int deleteEnemy(Enemy enemy);
    public List<Enemy> getEnemies();

}
