package ormtests;

import edu.upc.eetac.dsa.orm.dao.IEnemyDAO;
import edu.upc.eetac.dsa.orm.dao.EnemyDAOImpl;
import edu.upc.eetac.dsa.orm.model.Enemy;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyDAOImplTest {

    private static final Logger logger = Logger.getLogger(IEnemyDAO.class);
    IEnemyDAO EnemyDAOImpl = new EnemyDAOImpl();
    Enemy enemy = null;
    String enemyId;
    @Before
    public void setUp() {
        enemy = new Enemy("Chort","",1,1,1);
        enemy.setAvatar("Test Avatar!");
        enemyId = EnemyDAOImpl.addEnemy(enemy);
    }
    //Tests
    //Test to add a new enemy
    @Test
    public void addEnemyTest(){
        logger.info("Add Enemy Test");
        String id = EnemyDAOImpl.addEnemy(enemy);
        Assert.assertNotNull(id);
        logger.info("Enemy Added");
    }

    @Test
    //Test to update an enemy
    public void updateEnemyTest(){
        logger.info("Update Enemy Test");
        enemy.setName("Chort Updates Test");
        int res = EnemyDAOImpl.updateEnemy(enemy);
        Assert.assertEquals(1,res);
        logger.info("Updated enemy");
    }

    @Test
    //Test to delete an Enemy
    public void deleteEnemyTest(){
        logger.info("Delete Enemy Test");
        int res = EnemyDAOImpl.deleteEnemy(enemy);
        Assert.assertEquals(1,res);
        logger.info("Enemy deleted");
    }


    @After
    public void tearDown() {
    }
}
