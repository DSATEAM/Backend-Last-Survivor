package edu.upc.eetac.dsa.services;
import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.managers.RankingManager;
import edu.upc.eetac.dsa.managers.RankingManagerImpl;
import edu.upc.eetac.dsa.orm.model.Player;
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

@Api(value = "/RankingService", description = "Endpoint to Ranking Service")
@Path("/rank")
public class RankingService {
    static final Logger logger = Logger.getLogger(RankingService.class);
    private final RankingManager manager;

    public RankingService() {
        manager = RankingManagerImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get Players", notes = "Gets players Stats from database, no password or id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @Path("/global")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<Player> list = this.manager.getPlayers();
        if (list == null) return Response.status(404).build();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(list) {};
        return Response.status(201).entity(entity).build();
    }
}
