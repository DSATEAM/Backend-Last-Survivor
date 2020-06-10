package managertests;

import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.Player;
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
        Player player = new Player("Erica", "Nakiri", 0, 0, 0, 0);
        player.setAvatar("kjjkkj");
        ID = playerDAO.addPlayer(player);
        String signInID = playerDAO.getId("Erica","Nakiri");
        Assert.assertEquals(ID,signInID);
        playerDAO.deletePlayer(ID);
    }
    @Test

    public void signUpTest(){
        logger.info("SignUp Test");
        Player player = new Player("Takyumi", "kichan", 0, 0, 0, 0);
        player.setAvatar("basicAvatar");
        player = manager.signUp(player);
        Assert.assertNotNull(player);
        Player player2 = manager.signUp(player);
        Assert.assertNull(player2);
        //String signInID = playerDAO.getId("kru","kru");
        playerDAO.deletePlayer(player.getId());
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
        Player player = new Player("Yukhira", "Soma", 0, 0, 0, 0);
        player.setAvatar("kjjkkj");
        player = manager.signUp(player);
        manager.deletePlayer(player.getId());
        Assert.assertNull(playerDAO.getId("Yukhira","Soma"));
    }
    @After
    public void tearDown() {
        manager = null;
    }
}
