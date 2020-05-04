package edu.upc.eetac.dsa.orm;

import edu.upc.eetac.dsa.RandomUtils;
import edu.upc.eetac.dsa.orm.util.ObjectHelper;
import edu.upc.eetac.dsa.orm.util.QueryHelper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;
    private static final int sizeID = 16;
    public SessionImpl(Connection conn) {
        this.conn = conn;
    }
    // Saves an Object(Player,Item,Material...) in DB excluded ListFields(use saveList for this)!z
    public String save(Object entity) {
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        RandomUtils randomUtils = new RandomUtils();
        PreparedStatement pstm = null;
        String ID = RandomUtils.generateID(sizeID);
        try {
            pstm = conn.prepareStatement(insertQuery);
            ObjectHelper.setter(entity,"ID",ID);

            int i = 1;
            //Only Primitive Types Int String
            for (String field: ObjectHelper.getStrFields(entity)) {
                Object objt = ObjectHelper.getter(entity, field);
                pstm.setObject(i, objt);
                i++;
            }
            pstm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ID;
    }
    //SAVES THE LIST OF OBJECT(Item,Materials) GIVEN Object(Player) Not Recursive
    public void saveList(Object entity){
        //For a Player which contains listItems, this will save all the items in the table items
        try {
            Object objList = null;
            //listObjects Relational Mapping to ObjectName Table
            for (Field field: ObjectHelper.getListFields(entity)) {
                objList = ObjectHelper.getter(entity,field.getName());
                //Class<?> cls = Class.forName("String");
                for (Object o :(List) objList) {
                    // Save the Object in Database in the Corresponding Table
                    save(o);
                }
            }
            //EXECUTE STATEMENT
            //REPEAT ABOVE STATEMENT TILL SIZE OF LIST OBJECTS
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // TODO FINISHED!
    public void close() {
        try {
            this.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Completed getObject(Player..) with list as null, use getList for that!
    public Object get(Class theClass, String ID) {

        Object obj = null; PreparedStatement pstm = null;
        //Instantiating a object of type class for the getters
        try {
            String selectQuery = QueryHelper.createQuerySELECT(theClass.newInstance());

            obj = theClass.newInstance();
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, ID);
            ResultSet resultSet =  pstm.executeQuery();int i = 0;
            //INVOKE SETTER FOR EACH CORRESPONDING PROPERTY OF THE TABLE TO MAP WITH OBJECT
            while (resultSet.next()){
                //SQL WILL NEVER RETURN LIST AS A RESULT
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(i=1;i<=resultSetMetaData.getColumnCount();i++){
                    String name = resultSetMetaData.getColumnName(i);
                       ObjectHelper.setter(obj,name, resultSet.getObject(i));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    // Given Class (Item,Player) with parentID returns list<Material,Item>
    public List<Object> getList(Class theClass,String parentID){

         PreparedStatement pstm = null;
        //Instantiating a object of type class for the getters
        List<Object> objList = new LinkedList<>();
        try {
            String selectQuery = QueryHelper.createParentIDQuerySELECT(theClass.newInstance());
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, parentID);
            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                Object obj = theClass.newInstance();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    String name = resultSetMetaData.getColumnName(i);
                    obj = ObjectHelper.setter(obj,name, resultSet.getObject(i));
                }
                //We have filled all of the fields inside the Object of type <E>
                objList.add(obj);
                String test = "";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return objList;
    }
    // TODO FINISH THE MOFICATION OF THE OBJECT GIVEN THE UPDATED OBJECT
    public void update(Object object) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        PreparedStatement pstm;
        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;
            Object obj;
            //Only Primitive Types Int String
            for (String field: ObjectHelper.getStrFields(object)) {
                obj = ObjectHelper.getter(object,field);
                pstm.setObject(i, obj);
                i++;
            }
            String field=ObjectHelper.getStrFields(object)[0];
            obj = ObjectHelper.getter(object,field);
            pstm.setObject(i, obj);
            pstm.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TODO FINISH THE DELETE OBJECT FROM DB GIVEN THE OBJECT
    public void delete(Object o) throws Exception {
        String delete = QueryHelper.createQueryDELETE(o);
        PreparedStatement pstm = null;
        try {
            pstm=conn.prepareStatement(delete);
            for(String field: ObjectHelper.getStrFields(o)){
                if(field.equals("ID")) {
                    pstm.setObject(1, ObjectHelper.getter(o, field));
                }
            }
            pstm.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    // TODO FINISH THE GET ALL OF THE DATA FROM DB GIVEN THE CLASS(model)
    public List<Object> findAll(Class theclass) throws SQLException {
        String findall = QueryHelper.createQuerySELECT(theclass);
        PreparedStatement pstm = null;
        try {
        pstm = conn.prepareStatement(findall);
        List<Object> tableNames = new ArrayList<>();
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            tableNames.add(rs.getObject(1));
        }
        return tableNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
    // TODO FINISH THE GET ALL OF THE DATA FROM DB GIVEN THE CLASS(model) & THE PARAMETERS WHICH MATCH THE HASH
    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }
    // TODO FINISH THE SUBJECTIVE QUEURY WHICH WILL BE PASS THROUGH AS A SENTENCE(USE STATEMENTS)
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
