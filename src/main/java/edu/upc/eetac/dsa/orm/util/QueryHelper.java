package edu.upc.eetac.dsa.orm.util;

import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QueryHelper {

    public static String[] createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");
        List<String> fieldsOrdered = new LinkedList<>();

        String[] fields = ObjectHelper.getStrFields(entity);
        for (String field: fields) {
            sb.append(field);
            fieldsOrdered.add(field);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");
        for (String field: fields) {
            if(field.equals("password")){
                sb.append("AES_ENCRYPT(?,'SALTED_CHARACTER_SECRET_KEY'),");
            }
            else {
                sb.append("?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");

        String[] returnArr = new String[fieldsOrdered.size()+1];
        returnArr[0]= sb.toString();
        for(int k = 0;k<fieldsOrdered.size();k++){
            returnArr[k+1] = fieldsOrdered.get(k);
        }
        return returnArr;
    }

    public String stringifyList(List<String> listStr){
        StringBuilder rs = new StringBuilder();
        for (String str : listStr) {
            rs.append(',').append(str);
        }
        return rs.toString();
    }
    public List<String> StrCommaSeparetedToList(String resultStr){
        List<String> rl = new LinkedList<String>();
        String[] arrID = resultStr.split(",");
        rl = Arrays.asList(arrID);
        return rl;
    }
    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE id = ?");

        return sb.toString();
    }
    /*public static String createLoginQuerySELECT(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) AS IS_USER FROM USER WHERE username = ? AND password = MD5(?)");
        return sb.toString();
    }*/

    //SELECT md5(password ) AS pass, username FROM USER WHERE username = ?
    //SELECT count(*) FROM USER WHERE username = ? AND password = md5(?)
    //SELECT count(*) AS IS_USER FROM USER WHERE username = ? AND password = md5(?)
    public static String createQuerySELECTALL(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        return sb.toString();
    }
    public static String createParentIdQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE parentId = ?");
        return sb.toString();
    }
    public static String[] createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        List<String> fieldsOrdered = new LinkedList<>();
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");
        String [] fields = ObjectHelper.getStrFields(entity);

        for (String field: fields) {
            if(field.equals("password")){
                sb.append(field).append(" = AES_ENCRYPT(?,'SALTED_CHARACTER_SECRET_KEY'),");
                fieldsOrdered.add("password");
            }
            else if(!field.equals("id")){//Do Nothing as don't want to change the ID
                fieldsOrdered.add(field);
                sb.append(field).append(" = ?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE id = ?");
        fieldsOrdered.add("id");
        String[] returnArr = new String[fieldsOrdered.size()+1];
        returnArr[0]= sb.toString();
        for(int k = 0;k<fieldsOrdered.size();k++){
            returnArr[k+1] = fieldsOrdered.get(k);
        }
        return returnArr;
    }

    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

}
