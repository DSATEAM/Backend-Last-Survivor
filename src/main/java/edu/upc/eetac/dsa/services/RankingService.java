package edu.upc.eetac.dsa.services;
import edu.upc.eetac.dsa.managers.PlayerManager;
import edu.upc.eetac.dsa.managers.PlayerManagerImpl;
import edu.upc.eetac.dsa.managers.RankingManager;
import edu.upc.eetac.dsa.managers.RankingManagerImpl;
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
import java.lang.reflect.Field;
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
            @ApiResponse(code = 201, message = "Successful", response = RankingDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @Path("/global")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<RankingDTO> list = this.manager.getPlayers();
        logger.info("Ranking: "+list);
        if (list == null|| list.equals("[]")) return Response.status(404).build();
        GenericEntity<List<RankingDTO>> entity = new GenericEntity<List<RankingDTO>>(list) {};
        return Response.status(201).entity(entity).build();
    }
}
