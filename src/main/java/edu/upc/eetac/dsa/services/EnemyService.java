package edu.upc.eetac.dsa.services;


import edu.upc.eetac.dsa.managers.EnemyManager;
import edu.upc.eetac.dsa.managers.EnemyManagerImpl;

import edu.upc.eetac.dsa.orm.model.Enemy;


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

@Api(value = "/EnemyService", description = "Endpoint to Enemy Service")
@Path("/enemy")
public class EnemyService {

    static final Logger logger = Logger.getLogger(EnemyService.class);
    private final EnemyManager manager;
    public EnemyService(){
        manager = EnemyManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "add Enemy", notes = "Adds a new Enemy to db given Enemy")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class),
            @ApiResponse(code = 409, message = "Conflict, Enemy Exists with same name"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/addEnemy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnemies(Enemy enemy) {
        //Checking if anything null or empty which shouldn't be!
        if(enemy.getName() ==null || enemy.getDescription()==null|| enemy.getAvatar()==null) return Response.status(400).build();
        if(enemy.getName().equals("") || enemy.getDescription().equals("")|| enemy.getAvatar().equals("")
                ||enemy.getName().isEmpty()|| enemy.getDescription().isEmpty()|| enemy.getAvatar().isEmpty())  return Response.status(400).build();
        //Check if enemy with same name exists!
        Enemy tmp = manager.getEnemyFromName(enemy);
        //Adding Map
        if(tmp==null) {
            String mapId = this.manager.addEnemy(enemy);
            logger.info("Added Enemy: " + enemy.toString());
            if (mapId == null) return Response.status(409).build();
            //Means we have added the enemy thus we can return the enemy as a object filled with its data and id
            enemy.setId(mapId);
            return Response.status(201).entity(enemy).build();
        }else{ //Map Exists with same name
            return Response.status(409).entity(enemy).build();
        }
    }
    @GET
    @ApiOperation(value = "getEnemy", notes = "gets Enemy in List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/getEnemies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {
        List<Enemy> list = this.manager.getEnemies();
        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(list) {};
        return Response.status(201).entity(entity).build();
    }
    @PUT
    @ApiOperation(value = "update Enemy", notes = "updates Enemy and returns code result")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updateEnemy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEnemy(Enemy enemy) {
        if (enemy == null )  return Response.status(400).build();
        int res = this.manager.updateEnemy(enemy);
        if(res == -1) return Response.status(404).build();

        return Response.status(201).entity(enemy).build();
    }
    @PUT
    @ApiOperation(value = "Delete Enemy", notes = "Delete Enemy given EnemyId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deleteEnemy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEnemy(Enemy enemy) {
        if(enemy.getId()==null || enemy.getName()==null) return Response.status(400).build();
        if (enemy.getId()=="" || enemy.getName()==""||enemy.getDescription().isEmpty())  return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        logger.info("Delete Enemy: Name "+enemy.getName()+" ,ID "+enemy.getId());
        int res = this.manager.deleteEnemy(enemy);
        if(res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }
}
