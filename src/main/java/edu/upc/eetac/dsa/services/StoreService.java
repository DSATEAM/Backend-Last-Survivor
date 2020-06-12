package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.StoreManager;
import edu.upc.eetac.dsa.managers.StoreManagerImpl;
import edu.upc.eetac.dsa.orm.model.Item;
import edu.upc.eetac.dsa.orm.model.Player;
import edu.upc.eetac.dsa.orm.model.RankingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/StoreService", description = "Endpoint to Store Service")
@Path("/inventory")
public class StoreService {
    static final Logger logger = Logger.getLogger(StoreService.class);
    private final StoreManager manager;
    public StoreService(){
        manager = StoreManagerImpl.getInstance();
    }
    //Add Item to Player
    @POST
    @ApiOperation(value = "addItem", notes = "Adds Item to Player given with Items in List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 402, message = "Not Enough Credits"),
            @ApiResponse(code = 409, message = "Conflict, Item Exists in Player"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/addItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        if(item.getId()==null || item.getParentId()==null)  return Response.status(400).build();
        if(item.getId().equals("")||item.getParentId().equals("")||item.getParentId().isEmpty()||item.getId().isEmpty()) return Response.status(400).build();
        //Check Credit
        int resCode = this.manager.checkPurchase(item);
        if(resCode == 0) return Response.status(409).build();//"Conflict, Items Exists in Player"
        if(resCode == -1) return Response.status(402).build();//"Not Enough Credits"
        this.manager.addItem(item);
        return Response.status(201).entity(item).build();
    }
    @GET
    @ApiOperation(value = "getItems", notes = "gets Item in List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/getItems")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        List<Item> list = this.manager.getItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(list) {};
        return Response.status(201).entity(entity).build();
    }
    //  DELETE ITEM
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
        if(player.getPassword()==null || player.getUsername()==null|| player.getId() ==null || player.getUsername()=="" || player.getPassword()==""||player.getUsername().isEmpty()|| player.getPassword().isEmpty()
                || player.getId()==""||player.getId().isEmpty())  return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        /*logger.info("Delete User: Username "+player.getUsername()+" ,Password "+player.getPassword());
        String playerId = this.manager.signIn(player.getUsername(),player.getPassword());
        int res = this.manager.deletePlayer(playerId);
        if(res == -1) return Response.status(404).build();*/
        return Response.status(201).build();
    }
}
