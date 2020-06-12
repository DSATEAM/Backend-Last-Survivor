package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.managers.ForumManager;
import edu.upc.eetac.dsa.managers.ForumManagerImpl;
import edu.upc.eetac.dsa.managers.MessageManager;
import edu.upc.eetac.dsa.managers.MessageManagerImpl;
import edu.upc.eetac.dsa.orm.model.Forum;
import edu.upc.eetac.dsa.orm.model.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.indirection.BackupValueHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/ForumService", description = "Endpoint to Forum Service")
    @Path("/forum")
    public class ForumService {
    static final Logger logger = Logger.getLogger(edu.upc.eetac.dsa.services.ForumService.class);
    private final ForumManager manager;
    private final MessageManager messageManager;

    public ForumService() {
        manager = ForumManagerImpl.getInstance();
        messageManager = MessageManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "createForum", notes = "Adds a new Forum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Forum.class),
            @ApiResponse(code = 409, message = "Conflict, User Exists"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/createForum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createForum(Forum forum) {

        if (forum.getName() == null || forum.getAdmin() == null || forum.getAvatar() == null)
        {   return Response.status(400).build();}
        if(forum.getName().equals("") || forum.getAdmin().equals("") ||  forum.getAvatar().equals("") ||forum.getName().isEmpty() || forum.getAdmin().isEmpty() || forum.getAvatar().isEmpty())
        { return Response.status(400).build();}
        String forumId = this.manager.createForum(forum);
        logger.info("createForum-> " + forum);
        if (forumId == null) return Response.status(409).build();
        //Means we have found the player thus we can return the player as a object filled with its data
        forum = this.manager.getForum(forumId);
        return Response.status(201).entity(forum).build();
    }

    @PUT
    @ApiOperation(value = "update Forum", notes = "updates Forum with new messages")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Forum.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/updateForum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateForum(Forum forum) {
        if (forum == null) return Response.status(400).build();
        forum = this.manager.updateForum(forum);
        if (forum == null) return Response.status(404).build();
        return Response.status(201).entity(forum).build();
    }

    @PUT
    @ApiOperation(value = "Delete Forum", notes = "Delete Forum by the admin")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @Path("/deleteForum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteForum(Forum forum) {
        if (forum.getId() == null || forum.getId().equals("")|| forum.getId().isEmpty())
            return Response.status(400).build();
        //Not Authorized or no user with the password or Vice versa
        logger.info("Delete Forum->Name: " + forum);
        int res = this.manager.deleteForum(forum);
        if (res == -1) return Response.status(404).build();
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "getForums", notes = "gets Forums in List")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Forum.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/getForums")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForums() {
        List<Forum> list = this.manager.getForums();
        GenericEntity<List<Forum>> entity = new GenericEntity<List<Forum>>(list) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "addMessage", notes = "Adds Message to Forum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Forum.class),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/addMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message) {
        logger.info("Add Message-> "+message.toString());
        if (message.getParentId() == null||message.getUsername()== null||message.getAvatar()==null) return Response.status(400).build();
        this.messageManager.addMessage(message);
        return Response.status(201).entity(this.manager.getForum(message.getParentId())).build();
    }

    @PUT
    @ApiOperation(value = "getMessages", notes = "gets Messages in a Forum")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Message.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @Path("/getMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(Forum forum) {
        List<Message> list = this.messageManager.getMessages(forum.getId());

        GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(list) {
        };
        return Response.status(201).entity(entity).build();
    }
}

