package ormtests;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.model.Player;
//Junit 4.13
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerDAOImplTest{
    PlayerDAOImpl playerDAO ;
    Player player = null;
    @Before
    public void setUp() {
        player = null;
        playerDAO = new PlayerDAOImpl();
    }
    @Test
    public void addPlayerTest(){
        String playerID = playerDAO.addPlayer("Marc", "pass", 5, 4, 2, 23);
        Assert.assertNotNull(playerID);
        playerDAO.deletePlayer(playerID);
    }
    @Test
    public void getPlayerTest(){
       String playerId = playerDAO.addPlayer("Yo", "pass", 5, 4, 2, 23);
       Object obj = playerDAO.getId("Yo","pass");
       Assert.assertNotNull(playerDAO.getPlayer(playerId));
       playerDAO.deletePlayer(playerId);
    }
    @After
    public void tearDown() {
    }

}