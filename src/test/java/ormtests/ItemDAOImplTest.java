package ormtests;
import edu.upc.eetac.dsa.orm.dao.IItemDAO;
import edu.upc.eetac.dsa.orm.dao.ItemDAOImpl;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
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
    Material mat,mat2;
    @Before
    public void setUp() {
        item = null;
        item = new Item("007","Casino9","Ranged","Extinct","Long lost Item",null,2,0);
        mat = new Material("TestMaterialForItem1", "JustTesting", 42);
        mat2 = new Material("TestMaterialForItem2", "JustTesting2", 22);
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
    public void getInItemMaterialsTest(){
        itemId = ItemDAOImpl.addItem(item);
        mat.setParentID(itemId);
        mat2.setParentID(itemId);
        item.addListMaterial(mat);item.addListMaterial(mat2);
        item = ItemDAOImpl.addItemMaterials(item);
        List<Material> mats = ItemDAOImpl.getListMaterialsByItemID(itemId);
        Assert.assertEquals(2,mats.size());
        ItemDAOImpl.deleteMaterialItem(mat);ItemDAOImpl.deleteMaterialItem(mat2);
        Assert.assertEquals(0,ItemDAOImpl.getListMaterialsByItemID(itemId).size());
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
    @Test
    public void addMaterialsFromItem(){
        itemId = ItemDAOImpl.addItem(item);
        mat.setParentID(itemId);
        mat2.setParentID(itemId);
        item.addListMaterial(mat);item.addListMaterial(mat2);
        item = ItemDAOImpl.addItemMaterials(item);
        Assert.assertNotNull(item.getListMaterials().get(0).getId());
        ItemDAOImpl.deleteMaterialItem(mat);ItemDAOImpl.deleteMaterialItem(mat2);
        Assert.assertEquals(0,ItemDAOImpl.getListMaterialsByItemID(itemId).size());
        ItemDAOImpl.deleteItem(itemId);
    }
    @Test
    public void updateItemMaterialsTest(){

        itemId = ItemDAOImpl.addItem(item);
        mat.setParentID(itemId);
        mat2.setParentID(itemId);
        List<Material> mats =new  LinkedList<Material>();
        mats.add(mat);mats.add(mat2);
        item.addListMaterial(mat);item.addListMaterial(mat2);
        item = ItemDAOImpl.addItemMaterials(item);
        //Now we can Modify actually Materials and than call update
        mat.setName("ChangeMat1Name");mat2.setQuantity(10);
        mats.set(0,mat);mats.set(1,mat2);
        item.setListMaterials(mats);
        int res = ItemDAOImpl.updateItemMaterials(item);
        Assert.assertEquals(1,res);
        ItemDAOImpl.deleteItem(itemId);
        ItemDAOImpl.deleteMaterialItem(mat);ItemDAOImpl.deleteMaterialItem(mat2);
    }
    @After
    public void tearDown() {
        item = null;
        mat = null; mat2 = null;
    }
}
