package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 409, message = "Conflict, User Exists"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/signUp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(Player player) {
        logger.info("signUp: Username "+player.getUsername()+" ,Password "+player.getPassword());
        if(player.getPassword()==null || player.getUsername()==null) return Response.status(400).build();
        System.out.println("SignUp "+ player.toString());
        if(player.getUsername()=="" || player.getPassword()==""||player.getUsername().isEmpty()|| player.getPassword().isEmpty())  return Response.status(400).build();
        String playerId = this.manager.signUp(player.getUsername(),player.getPassword());
        if(playerId == null) return Response.status(409).build();
        //Means we have found the player thus we can return the player as a object filled with its data
        player = this.manager.getPlayer(playerId);
        return Response.status(201).entity(player).build();
    }
    @POST
    @ApiOperation(value = "signIn Player", notes = "Retrieves the Player ID from username,password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Player.class),
            @ApiResponse(code = 401, message = "Not Authorized", response = Player.class),
    })
    @Path("/signIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(Player player) {

        if(player.getPassword()==null || player.getUsername()==null) return Response.status(400).build();
        if (player.getUsername()=="" || player.getPassword()==""||player.getUsername().isEmpty()|| player.getPassword().isEmpty())  return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        logger.info("signIn: Username "+player.getUsername()+" ,Password "+player.getPassword());
        String playerId = this.manager.signIn(player.getUsername(),player.getPassword());
        if(playerId == null) return Response.status(401).build();
        //Means we have found the player thus we can return the player as a object filled with its data
        player = this.manager.getPlayer(playerId);
        return Response.status(201).entity(player).build();
    }
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
    @DELETE
    @ApiOperation(value = "Delete Player", notes = "Delete Player given PlayerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deletePlayer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlayer(Player player) {
        if(player.getPassword()==null || player.getUsername()==null|| player.getId() ==null) return Response.status(400).build();
        if (player.getUsername()=="" || player.getPassword()==""||player.getUsername().isEmpty()|| player.getPassword().isEmpty()
                || player.getId()==""||player.getId().isEmpty())  return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        logger.info("Delete User: Username "+player.getUsername()+" ,Password "+player.getPassword());
        String playerId = this.manager.signIn(player.getUsername(),player.getPassword());
        int res = this.manager.deletePlayer(playerId);
        if(res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }
}
