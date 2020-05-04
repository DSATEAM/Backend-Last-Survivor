package ormtests;

import edu.upc.eetac.dsa.orm.dao.IEnemyDAO;
import edu.upc.eetac.dsa.orm.dao.EnemyDAOImpl;
import edu.upc.eetac.dsa.orm.model.Enemy;
import edu.upc.eetac.dsa.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EnemyDAOImplTest {

    private static final Logger logger = Logger.getLogger(IEnemyDAO.class);
    IEnemyDAO EnemyDAOImpl = new EnemyDAOImpl();
    Enemy enemy = null;
    String enemyID;
    @Before
    public void setUp() {
        enemy = null;
        enemyID = EnemyDAOImpl.addEnemy("Dragon", "It can spit fire and burn you to death but it is weak against water weapons.",5, 50);
    }
    //Tests
    //Test to add a new enemy
    @Test
    public void addEnemyTest(){
        logger.info("Add Enemy Test");
        String ID = EnemyDAOImpl.addEnemy("Demon","It can kill you with his spear but it is weak against light and water weapons.",3, 20);
        Assert.assertNotNull(ID);
        logger.info("Enemy Added");
    }
    @Test
    //Test to get an enemy
    public void getEnemyTest(){
        logger.info("Get Enemy Test");
        String ID = EnemyDAOImpl.addEnemy("Demon", "It can kill you with his spear but it is weak against light and water weapons.", 3, 20);
        logger.info("Enemy with ID = " +ID);
        enemy = EnemyDAOImpl.getEnemy(ID);
        Assert.assertNotNull(enemy);
        logger.info(enemy.toString());
    }

    @Test
    //Test to update an enemy
    public void updateEnemyTest(){
        logger.info("Update Enemy Test");
        enemy = EnemyDAOImpl.getEnemy(enemyID);
        logger.info("Current enemy");
        logger.info(enemy.toString());
        int res = EnemyDAOImpl.updateEnemy(enemyID, "Orc","It can kill you with his axe but it very sensitive to light weapons", 2 , 10);
        Assert.assertEquals(1,res);
        enemy = EnemyDAOImpl.getEnemy(enemyID);
        logger.info("Updated enemy");
        logger.info(enemy.toString());


    }

    @Test
    //Test to delete an Enemy
    public void deleteEnemyTest(){
        logger.info("Delete Enemy Test");
        int res = EnemyDAOImpl.deleteEnemy(enemyID);
        Assert.assertEquals(1,res);
        logger.info("Enemy deleted");
    }


    @After
    public void tearDown() {
    }
}
