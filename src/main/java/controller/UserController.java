package controller;

import java.util.Optional;

import dto.CreateUserDto;
import entity.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mapper.UserMapper;
import service.UserService;

@Path("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @POST
    @Produces()
    public Response createUser(CreateUserDto createUser) {
        var entity = userMapper.fromCreate(createUser);
        userService.createUser(entity);
        
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var dto = userMapper.fromUser(user.get());
        return Response.ok().entity(dto).build();
    }
}
