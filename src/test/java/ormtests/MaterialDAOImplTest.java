package ormtests;
import edu.upc.eetac.dsa.orm.*;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.dao.*;
import edu.upc.eetac.dsa.orm.model.Material;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
public class MaterialDAOImplTest{
    MaterialDAOImpl materialdao= new MaterialDAOImpl();
    Material material = null;
    @Before
    public void setUp() {
        material = null;
    }
    @Test
    public void addMaterialTest(){
        Assert.assertNotNull(materialdao.addMaterial("Madera","Es madera", 2));
    }
    @Test
    public void getMaterialTest(){
        String id= materialdao.addMaterial("Hierro", "Duro como el acero", 4);
        Assert.assertNotNull(materialdao.getMaterial(id));
    }
    @Test
    public void getMaterialsTest(){
        Assert.assertNotNull(materialdao.getAllMaterials());
    }
    @After
    public void tearDown() {
    }

}