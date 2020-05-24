package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.StoreManager;
import edu.upc.eetac.dsa.managers.StoreManagerImpl;
import edu.upc.eetac.dsa.orm.model.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/StoreService", description = "Endpoint to Store Service")
@Path("/inventory")
public class StoreService {
    static final Logger logger = Logger.getLogger(StoreService.class);
    private final StoreManager manager;
    public StoreService(){
        manager = StoreManagerImpl.getInstance();
    }

    @DELETE
    @ApiOperation(value = "Delete Item", notes = "Delete Item given Player")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deleteItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(Player player) {
        if(player.getPassword()==null || player.getUsername()==null|| player.getId() ==null) return Response.status(400).build();
        if (player.getUsername()=="" || player.getPassword()==""||player.getUsername().isEmpty()|| player.getPassword().isEmpty()
                || player.getId()==""||player.getId().isEmpty())  return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        /*logger.info("Delete User: Username "+player.getUsername()+" ,Password "+player.getPassword());
        String playerId = this.manager.signIn(player.getUsername(),player.getPassword());
        int res = this.manager.deletePlayer(playerId);
        if(res == -1) return Response.status(404).build();*/
        return Response.status(201).build();
    }
}
