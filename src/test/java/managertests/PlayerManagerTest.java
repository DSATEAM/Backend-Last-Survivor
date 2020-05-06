package managertests;

import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerManagerTest {
    private static final Logger logger = Logger.getLogger(PlayerManagerTest.class);
    //IEnemyDAO EnemyDAOImpl = new EnemyDAOImpl();
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    PlayerManager manager;
    @Before
    public void setUp() {
        manager = PlayerManagerImpl.getInstance();
    }
    //Tests
    //Test to add a new enemy
    @Test
    public void signInTest(){
        logger.info("SignIn Test");
        String ID;
        ID = playerDAO.addPlayer("kru", "kru", 0, 0, 0, 0, 0);
        String signInID = playerDAO.getID("kru","kru");
        Assert.assertEquals(ID,signInID);
        playerDAO.deletePlayer(ID);
    }
    @Test
    //Test to get an enemy
    public void signUpTest(){
        logger.info("SignUp Test");
        int res = manager.signUp("kru","kru");
        Assert.assertEquals(1,res);
        res = manager.signUp("kru","kru");
        Assert.assertEquals(-1,res);
        String signInID = playerDAO.getID("kru","kru");
        playerDAO.deletePlayer(signInID);
    }
    @Test
    //Test to get an enemy
    public void signOutTest(){
        logger.info("SignOut Test-->What's the use?");
    }
    @Test
    //Test to get an enemy
    public void updatePlayerTest(){
        logger.info("Update Player Test-->Same as PlayerDAOImpl");

    }
    @Test
    //Test to get an enemy
    public void deletePlayerTest(){
        logger.info("Delete Player Test");
        int res = manager.signUp("kru","kru");
        String signInID = playerDAO.getID("kru","kru");
        manager.deletePlayer(signInID);
        Assert.assertNull(playerDAO.getID("kru","kru"));
    }
    @After
    public void tearDown() {
        manager = null;
    }
}
