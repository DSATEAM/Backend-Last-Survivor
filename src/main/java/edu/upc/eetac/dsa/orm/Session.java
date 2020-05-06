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
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass) ;
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> queryExecute(Class theClass, String queryExecute, List<Object> params);
}
