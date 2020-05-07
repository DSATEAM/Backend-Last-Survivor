package ormtests;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import edu.upc.eetac.dsa.orm.dao.IMapDAO;
import edu.upc.eetac.dsa.orm.dao.MapDAOImpl;
import edu.upc.eetac.dsa.orm.model.Map;


public class MapDAOImplTest {

    IMapDAO MapDAOImpl= new MapDAOImpl();
    Map map1= null;
    String mapID;

    @Before

    public void setUp()  {
        map1=null;
        mapID= MapDAOImpl.addMap("DUNGEON",500,500,"Stringmapadiseño");
    }

    @Test
    public void addMapTest() {

        String ID = MapDAOImpl.addMap("DESIERTO",500,500,"STRINGDELMAPA");
        Assert.assertNotNull(ID);
    }

    @Test
    public void getMapTest() {
        String ID = MapDAOImpl.addMap("DESIERTO",500,500,"STRINGDELMAPA");
        map1= MapDAOImpl.getMap(ID);
        Assert.assertNotNull(map1);
    }

    @Test
    public void updateMapTest() {
        map1 = MapDAOImpl.getMap(mapID);
        int res= MapDAOImpl.updateMap(mapID,500,500,"STRINGDELMAPA"); //añadir un valor res a update para comparar
        Assert.assertEquals(1,res);
        map1=MapDAOImpl.getMap(mapID);
    }

    @Test
    public void deleteMapTest() {
        int res= MapDAOImpl.deleteMap(mapID,500,500,"STRINGDELMAPA");
        Assert.assertEquals(1,res);
    }

    @Test
    public void getMapsTest() {

    }
    @After
    public void tearDown()  {
        map1= null;
    }
}