package ormtests;
import edu.upc.eetac.dsa.orm.*;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.Material;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
public class PlayerDAOImplTest{
    PlayerDAOImpl playerdao = new PlayerDAOImpl();
    Player player = null;
    @Before
    public void setUp() {
        player = null;
    }
    @Test
    public void addPlayerTest(){
        Assert.assertNotNull(playerdao.addPlayer("Marc", "pass", 5, 4, 2, 23, 2));
    }
    @Test
    public void getPlayerTest(){
       String Id = playerdao.addPlayer("Yo", "pass", 5, 4, 2, 23, 2);
       Assert.assertNotNull(playerdao.getPlayer(Id));
    }
    @Test
    public void getPlayersTest(){
        Assert.assertNotNull(playerdao.getPlayers());
    }
    @After
    public void tearDown() {
    }

}