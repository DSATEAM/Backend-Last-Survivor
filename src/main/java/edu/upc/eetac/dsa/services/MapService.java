package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.MapManager;
import edu.upc.eetac.dsa.managers.MapManagerImpl;
import edu.upc.eetac.dsa.orm.model.Map;

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
@Api(value = "/MapService", description = "Endpoint to Map Service")
@Path("/map")
public class MapService {
    static final Logger logger = Logger.getLogger(MapService.class);
    private final MapManager manager;
    public MapService(){
        manager = MapManagerImpl.getInstance();
    }
    //Services
    @POST
    @ApiOperation(value = "add Map", notes = "Adds a new Map to db given map")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Map.class),
            @ApiResponse(code = 409, message = "Conflict, Map Exists with same name"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/addMap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMap(Map map) {
       //Checking if anything null or empty which shouldn't be!
        if(map.getName() ==null || map.getType1MapGrid()==null|| map.getType2EntityPositions()==null|| map.getType2PlayerPosition()==null) return Response.status(400).build();
        if(map.getName()=="" || map.getType1MapGrid()==""|| map.getType2EntityPositions()==""||map.getType2PlayerPosition() ==""
                ||map.getName().isEmpty()|| map.getType1MapGrid().isEmpty()|| map.getType2EntityPositions().isEmpty()|| map.getType2PlayerPosition().isEmpty())  return Response.status(400).build();
        //Check if map with same name exists!
        Map tmp = manager.getMapFromName(map);
        //Adding Map
        if(tmp==null) {
            String mapId = this.manager.addMap(map);
            logger.info("Maps: " + map.toString());
            if (mapId == null) return Response.status(409).build();
            //Means we have added the map thus we can return the map as a object filled with its data and id
            map.setId(mapId);
            return Response.status(201).entity(map).build();
        }else{ //Map Exists with same name
            return Response.status(409).entity(map).build();
        }
    }

    @GET
    @ApiOperation(value = "Get Maps", notes = "Gets Maps from database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Map.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Map Bad Request"),
            @ApiResponse(code = 404, message = "Maps Not Found")
    })
    @Path("/getMaps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaps() {
        List<Map> list = this.manager.getMaps();
        logger.info("Maps: "+list);
        if (list == null) return Response.status(404).build();
        GenericEntity<List<Map>> entity = new GenericEntity<List<Map>>(list){};
        return Response.status(201).entity(entity).build();
    }
    @PUT
    @ApiOperation(value = "update Map", notes = "updates Map and returns code result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Map Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updateMap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMap(Map map) {
        if (map ==null )  return Response.status(400).build();
        Map tmp = this.manager.getMap(map);
        if(tmp.getName() == null)return Response.status(404).build();
        int res  = this.manager.updateMap(map);
        if(res == -1) return Response.status(404).build();
        logger.info("Maps: "+map.toString());
        return Response.status(201).build();
    }
    @PUT
    @ApiOperation(value = "Delete Map", notes = "updates Map and returns code result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Map Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deleteMap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMap(Map map) {
        if(map.getId() == null){ return Response.status(400).build();}
        if (map.getId().equals("")||map.getId().isEmpty()){  return Response.status(400).build();}
        //Not Authorized or no user with the password or Vice versa
        logger.info("Delete Map: Map id "+map.getId());
        Map tmp = this.manager.getMap(map);
        if(tmp.getName() == null)return Response.status(404).build();
        int res = this.manager.deleteMap(map);
        if(res == -1) {return Response.status(404).build();}
        return Response.status(201).build();
    }

}
