package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.orm.dao.IPlayerDAO;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Api(value = "/PlayerService", description = "Endpoint to Player Service")
@Path("/access")
public class PlayerService {
    static final Logger logger = Logger.getLogger(PlayerService.class);
    private IPlayerDAO manager;
    //Estructura de datos
    Player brote;
    List<Player> listaCasos;
    public PlayerService(){
        //manager = PlayerDAOImpl.getInstance();
    }
    @POST
    @ApiOperation(value = "Add a new Player", notes = "Adds a new Player given name, password(hashed) & email")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/addUser/{username}/{password}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("username") String username,@PathParam("password") String password,@PathParam("email") String email ) {
        if (username.isEmpty() || password.isEmpty())  return Response.status(400).build();
       // this.manager.añadirBrote(idBrote,nameBrote,new LinkedList<Caso>());
        return Response.status(201).build();
    }
}
