package edu.upc.eetac.dsa;

import io.swagger.jaxrs.config.BeanConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.sql.*;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
// REST AND SWAGGER

public class Main {
    static final Logger logger = Logger.getLogger(Main.class);
    public static final String host_address = "192.168.1.133:8080"; //Substitute with 147.83.7.205
    public static final String BASE_URI = "http://"+host_address+"/Backend/";
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.eetac.dsa.services");

        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();

        beanConfig.setHost(host_address);
        beanConfig.setBasePath("/Backend");
        beanConfig.setContact("DSA_3_Team@estudiantat.upc.edu");
        beanConfig.setDescription("REST API for Backend LastSurvivor");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setResourcePackage("edu.upc.eetac.dsa.services");
        beanConfig.setTermsOfServiceUrl("http://www.example.com/resources/eula");
        beanConfig.setTitle("REST API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setScan(true);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args - Argument
     * @throws IOException -Throws IOException
     */
    public static void main(String[] args) throws Exception {
        //Log4j initialization with proper configuration
        //PropertiesConfigurator is used to configure logger from properties file
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        // Server Initialization Code
        final HttpServer server = startServer();
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        // System.out.println(String.format("Jersey app started with WADL available at "
        //   + "%s application.wadl\nHit enter to stop it...", BASE_URI));
        //Formatting BASE_URI FOR SWAGGER
        String swagger_uri = "http://"+host_address+"/swagger3/";
        System.out.println(String.format("RestApi Started at " + "%s\nHit enter to stop it...", swagger_uri));
         System.in.read();
        server.shutdownNow();
        
    }
}

class DBJDBC2{
    public DBJDBC2(){}//Empty Constructor
    // FORMA MENOS PRIMITIVA QUITAMOS LA POSSIBILIDAD DE SQL INJECTION
    public static void insert() throws Exception {
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            //SQL INJECTION SOLVED
            String theQuery = "INSERT INTO User (ID,lastname,firstname,address,city) VALUE (0,?,?,?,?)";
            PreparedStatement statement1 =  connection.prepareStatement(theQuery);
            statement1.setString(1,"Pan");
            statement1.setString(2,"Peter");
            statement1.setString(3,"Imaginary Alley");
            statement1.setString(4,"Neverland");
            statement1.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){connection.close();}
        }
    }
}

class DBJDBC {
    // FORMA PRIMITIVA CON POSSIBILIDAD DE SQL INJECTION
    public static void insert() throws Exception {
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            Statement statement1 = connection.createStatement();
            statement1.execute("INSERT INTO User (ID,lastname,firstname,address,city) VALUE (1,'Pan','Peter','Imaginary Alley','Neverland')");
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){connection.close();}
        }
    }
    public static void findall()throws Exception {
        Connection connection = null;
        try{
            connection = DBUtils.getConnection();
            Statement statement2 = connection.createStatement();
            ResultSet rs = statement2.executeQuery("SELECT * FROM User WHERE 1=1");
            int id;
            String lastname,address,city,firstname;
            while (rs.next()){
                id = (Integer) rs.getObject(1);
                lastname = (String) rs.getObject(2);
                firstname = (String) rs.getObject(3);
                address = (String) rs.getObject(4);
                city = (String) rs.getObject(5);
                System.out.println("ID: "+id+" lastname: "+lastname+" firstname: "+firstname+" address: "+address+" city: "+city);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }

    public void DBJDBC(){}
}
class DBUtils{

    public static Connection getConnection() {
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

