package ormtests;
import edu.upc.eetac.dsa.orm.dao.IItemDAO;
import edu.upc.eetac.dsa.orm.dao.ItemDAOImpl;
import edu.upc.eetac.dsa.orm.model.Item;
import org.apache.log4j.Logger;
//Junit 4.13
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
public class ItemDAOImplTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static final Logger logger = Logger.getLogger(IItemDAO.class);
    IItemDAO ItemDAOImpl = new ItemDAOImpl();
    Item item = null;
    String itemId;
    @Before
    public void setUp() {
        item = null;
        item = new Item("007","Casino9","Ranged","Extinct","Long lost Item",50,2,0);
    }
    //Tests
    //Metodo Test para crear un nuevo brote
    @Test
    public void addItemTest(){
       itemId =  ItemDAOImpl.addItem(item);
        Assert.assertNotNull(itemId);
        ItemDAOImpl.deleteItem(itemId);
    }
    @Test
    public void getItemTest(){
        itemId = ItemDAOImpl.addItem(item);
        Assert.assertNotNull(ItemDAOImpl.getItem(itemId));
        ItemDAOImpl.deleteItem(itemId);
    }
    @Test
    //Test to delete an Item
    public void deleteItemTest(){
        logger.info("Delete Item Test");
        itemId =  ItemDAOImpl.addItem(item);
        int res = ItemDAOImpl.deleteItem(itemId);
        Assert.assertEquals(1,res);
        logger.info("Item deleted");
    }
    @Test
    //Test to delete an Item
    public void updateItemTest() {
        logger.info("Update Item Test");
        itemId =  ItemDAOImpl.addItem(item);
        item = ItemDAOImpl.getItem(itemId);
        logger.info("Current Item");
        logger.info(item.toString());
        item.setName("UpdatedTestName");
        int res = ItemDAOImpl.updateItem(item);
        Assert.assertEquals(1,res);
        item = ItemDAOImpl.getItem(itemId);
        logger.info("Updated item");
        logger.info(item.toString());
        ItemDAOImpl.deleteItem(itemId);
    }
    @After
    public void tearDown() {
        item = null;
    }
}
