package managertests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerManagerTest {
    private static final Logger logger = Logger.getLogger(PlayerManagerTest.class);
    //IEnemyDAO EnemyDAOImpl = new EnemyDAOImpl();
    @Before
    public void setUp() {
    }
    //Tests
    //Test to add a new enemy
    @Test
    public void signInTest(){
        logger.info("SignIn Test");
    }
    @Test
    //Test to get an enemy
    public void signUpTest(){
        logger.info("SignUp Test");

    }
    @Test
    //Test to get an enemy
    public void signOutTest(){
        logger.info("SignOut Test");
    }
    @Test
    //Test to get an enemy
    public void updatePlayerTest(){
        logger.info("Update Player Test");
    }
    @Test
    //Test to get an enemy
    public void deletePlayerTest(){
        logger.info("Delete Player Test");
    }
    @After
    public void tearDown() {
    }
}
