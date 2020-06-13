package edu.upc.eetac.dsa.orm.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String[] fields = ObjectHelper.getStrFields(entity);
        for (String field: fields) {
            sb.append(field);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");
        for (String field: fields) {
            if(field.equals("password")){
                sb.append("AES_ENCRYPT(?,'SALTED_CHARACTER_SET_SECRET_IN_A_WAY'),");
            }
            else {
                sb.append("?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");

        return sb.toString();
    }

    public String stringifyList(List<String> listStr){
        String rs = "";
        for (String str : listStr) {
            rs = rs + ',' + str;
        }
        return rs;
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
    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");
        String [] fields = ObjectHelper.getStrFields(entity);

        for (String field: fields) {
            if(field.equals("password")){
                //sb.append(field).append(" = AES_ENCRYPT(?,'SALTED_CHARACTER_SET_SECRET_IN_A_WAY'),");
            }
            else {
                sb.append(field).append(" = ?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("WHERE id = ?");
        return sb.toString();
    }

}
