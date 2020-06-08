package edu.upc.eetac.dsa.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjectHelper {
    //Returns Normal Types(Int,String..) Fields as String(Name)
    public static String[] getStrFields(Object entity) {

        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        ArrayList<String> sFields = new ArrayList<String>();
        int i=0;

        for (Field f: fields) {
            if(!f.getName().contains("list"))
               sFields.add(f.getName()) ;
        }
        String[] sFieldsArr = new String[sFields.size()];
        sFieldsArr = sFields.toArray(sFieldsArr);
        return sFieldsArr;
    }
    //Returns Normal Types(Int,String..) Fields as Fields
    public static Field[] getFields(Object entity) {

        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        List<Field> listFields = new LinkedList<>();
        int i=0;
        for (Field f: fields) {
            if(!f.getName().contains("list"))
                listFields.add(f) ;
        }
        Field[] tmp = new Field[listFields.size()];
        i = 0;
        for (Field f:listFields) {
            tmp[i] = listFields.get(i);
            i++;
        }
        return tmp;
    }
    //Returns list Fields as Fields
    public static Field[] getListFields(Object entity) {
        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        List<Field> listFields = new LinkedList<>();
        int i=0;
        for (Field f: fields) {
            if(f.getName().contains("list"))
                listFields.add(f) ;
        }
        Field[] tmp = new Field[listFields.size()];
        i = 0;
        for (Field f:listFields) {
            tmp[i] = listFields.get(i);
            i++;
        }
        return tmp;
    }
    public static Object setter(Object object, String property, Object value)  throws NoSuchMethodException{
        // Method
        Class<?> theClass = object.getClass();
        try{
        //Methods of type setters are usually like setProperty while Property is defined as property in the Object
        String gMethod = "set"+ property.substring(0,1).toUpperCase()+property.substring(1);
        Method setter = null;
        Class paramType = value.getClass();
            //If the property starts with the word list than parse the value to string as comma separated
            if(paramType == Integer.class){
                setter = theClass.getMethod(gMethod,Integer.TYPE);
                setter.invoke(object, value);
            }else if(paramType == Float.class){
                setter = theClass.getMethod(gMethod,Float.TYPE);
                setter.invoke(object, value);
            }else{
                setter = theClass.getMethod(gMethod,paramType);
                setter.invoke(object, value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public static Object getter(Object object, String property)throws Exception {
        // Method
        Object ret = null;
        Class theClass = object.getClass();
            //Methods of type getters are usually like getProperty while property is defined as property in the Object
            String sMethod = "get"+ property.substring(0,1).toUpperCase()+property.substring(1);
            Method getter = theClass.getMethod(sMethod);
            // Invoke
            ret = getter.invoke(object);
        return ret;
    }
}
