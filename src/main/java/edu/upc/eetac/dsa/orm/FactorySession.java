package edu.upc.eetac.dsa.orm;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactorySession {
    public static Session openSession() {
        Connection conn = getConnection();
        return new SessionImpl(conn);
    }
    private static Connection getConnection() {
        Connection conn = null;
        try
        {
            // Create Properties object.
            Properties databaseProperties = new Properties();
            String dbSettingsPropertyFile = "src/main/resources/dataBase.properties";
            // Properties will use a FileReader object as input.
            FileReader fReader = new FileReader(dbSettingsPropertyFile);
            // Load jdbc related properties in above file.
            databaseProperties.load(fReader);
            // Get each property value of DataBase.
            String host = databaseProperties.getProperty("db.host");
            String port = databaseProperties.getProperty("db.port");
            String dataBase = databaseProperties.getProperty("db.database");
            String user = databaseProperties.getProperty("db.username");
            String pass = databaseProperties.getProperty("db.password");
            conn = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+
                    dataBase+"?user="+user+"&password="+pass);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return conn;
    }
}
