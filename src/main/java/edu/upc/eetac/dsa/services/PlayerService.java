package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.orm.dao.IPlayerDAO;
import edu.upc.eetac.dsa.orm.dao.PlayerDAOImpl;
import edu.upc.eetac.dsa.orm.dao.IMaterialDAO;
import edu.upc.eetac.dsa.orm.dao.MaterialDAOImpl;
import edu.upc.eetac.dsa.orm.dao.IItemDAO;
import edu.upc.eetac.dsa.orm.dao.ItemDAOImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Material;
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
import javax.xml.bind.JAXBElement;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Api(value = "/PlayerService", description = "Endpoint to Player Service")
@Path("/access")
public class PlayerService {
    static final Logger logger = Logger.getLogger(PlayerService.class);
    private final PlayerManager manager;
    public PlayerService(){
        manager = PlayerManagerImpl.getInstance();
    }
    @POST
    @ApiOperation(value = "signUp", notes = "Adds a new Player given name, password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/signUp/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@PathParam("username") String username,@PathParam("password") String password ) {
        if (username.isEmpty() || password.isEmpty())  return Response.status(400).build();
        int res = this.manager.signUp(username,password);
        if(res==1) return Response.status(201).build();
        else return  Response.status(409).build();
    }
    @GET
    @ApiOperation(value = "signIn Player", notes = "Retrieves the Player ID from username,password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = String.class),
            @ApiResponse(code = 404, message = "Not Found", response = String.class),
            @ApiResponse(code = 401, message = "Not Authorized", response = String.class),
    })
    @Path("/signIn/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@PathParam("username") String username,@PathParam("password") String password ) {

        if (username.isEmpty() || password.isEmpty())  return Response.status(404).build();
        String ID = this.manager.signIn(username,password);
        if(ID.contains("")) return Response.status(401).entity(ID).build();
        return Response.status(201).entity(ID).build();
    }
    @GET
    @ApiOperation(value = "get Player", notes = "Retrieves the Player from PlayerID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Not Found", response = Player.class),
            @ApiResponse(code = 400, message = "Bad Request",response = Player.class)
    })
    @Path("/getPlayer/{playerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("playerID") String playerID ) {

        if (playerID.isEmpty() )  return Response.status(400).entity(new Player()).build();
        Player player = this.manager.getPlayer(playerID);
        if(player == null) return Response.status(404).entity(new Player()).build();
        return Response.status(201).entity(player).build();
    }
    //TODO PREGUNTAR POR COMO RECIBIR UN OBJETO
    @PUT
    @ApiOperation(value = "update Player", notes = "updates Player and returns code result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updatePlayer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlayer(Player player) {
        if (player ==null )  return Response.status(400).build();
        int res  = this.manager.updatePlayer(player);
        if(res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update Player", notes = "Retrieves the new Player given Fields")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updatePlayerFields/{playerID}/{username}/{password}/{gamesPlayed}/{kills}/{deaths}/{experience}/{wins}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlayer(@PathParam("playerID") String playerID ,@PathParam("username") String username
            ,@PathParam("password") String password,@PathParam("gamesPlayed") int gamesPlayed
            ,@PathParam("kills") int kills,@PathParam("deaths") int deaths,@PathParam("experience") int experience
            ,@PathParam("wins") int wins) {
        //As int cannot be null
        if (username.isEmpty()||playerID.isEmpty()||password.isEmpty() )  return Response.status(400).build();
        Player player = new Player(username,password,gamesPlayed,kills,deaths,experience,wins);
        player.setID(playerID);
        int res  = this.manager.updatePlayer(player);
        if(res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete Player", notes = "Delete Player given PlayerID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deletePlayer/{playerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlayer(@PathParam("playerID") String playerID) {
        if (playerID.isEmpty() ||playerID.equals("") )  return Response.status(400).entity(new Player()).build();
        int res = this.manager.deletePlayer(playerID);
        if(res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }
}
