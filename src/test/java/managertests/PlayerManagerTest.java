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
    PlayerDAOImpl playerDAO = new PlayerDAOImpl();
    PlayerManager manager;
    @Before
    public void setUp() {
        manager = PlayerManagerImpl.getInstance();
    }

    @Test
    public void signInTest(){
        logger.info("SignIn Test");
        String ID;
        ID = playerDAO.addPlayer("kru", "kru", 0, 0, 0, 0);
        String signInID = playerDAO.getId("kru","kru");
        Assert.assertEquals(ID,signInID);
        playerDAO.deletePlayer(ID);
    }
    @Test

    public void signUpTest(){
        logger.info("SignUp Test");
        String playerId= manager.signUp("kru","kru");
        Assert.assertNotNull(playerId);
        playerId= manager.signUp("kru","kru");
        Assert.assertNull(playerId);
        String signInID = playerDAO.getId("kru","kru");
        playerDAO.deletePlayer(signInID);
    }
    @Test
    public void signOutTest(){
        logger.info("SignOut Test-->What's the use?");
    }
    @Test
    public void updatePlayerTest(){
        logger.info("Update Player Test-->Same as PlayerDAOImpl");

    }
    @Test
    public void deletePlayerTest(){
        logger.info("Delete Player Test");
        String playerID = manager.signUp("kru","kru");
        manager.deletePlayer(playerID);
        Assert.assertNull(playerDAO.getId("kru","kru"));
    }
    @After
    public void tearDown() {
        manager = null;
    }
}
