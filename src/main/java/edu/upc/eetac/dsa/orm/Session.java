package edu.upc.eetac.dsa.orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    String save(Object entity);
    void close();
    Object get(Class theClass, String id);
    List<Object> getList(Class theClass,String parentId);
    void saveList(Object entity);
    int update(Object object);
    int delete(Object object);
    List<Object> findAll(Class theClass) ;
    List<Object> findAll(Class theClass, HashMap params);
    //Returns basic List of object types such as String and Integer
    List<Object> queryExecute(Class theClass, String queryExecute, List<Object> params);
    //Returns Object of Type Object Like Player,Item,Map given basic params
    List<Object> queryExecuteGetObject(Class theClass, String queryExecute, List params);
}
