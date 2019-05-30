package api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.domain.User;

@Path("/users")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value= MediaType.APPLICATION_JSON)
public class UserService {
	
	private static final List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1L, "oscar", "1234"));
		users.add(new User(2L, "oscar", "1234"));
		users.add(new User(3L, "oscar", "1234"));
	}
	
	@GET
	public Response findAllUsers() {
		return Response.ok(UserService.users).build();
	}
	
	@POST
	public Response createUser(User userRequest) {
		userRequest.setCodigo(users.size() + 1L);
		UserService.users.add(userRequest);
		return Response.ok(userRequest).build();
	}
	
	@PUT
	public Response updateUser(User userRequest) {
		List<User> found = UserService.users.stream().filter(
									x -> userRequest.getCodigo() == x.getCodigo()).collect(Collectors.toList());
		
		if(found.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("User no found").build();
		}
		
		User updateUser = found.get(0);
		updateUser.setPassword(userRequest.getPassword());
		updateUser.setNombre(userRequest.getNombre());
		
		return Response.ok(updateUser).build();
		
	}
	
	@DELETE
	@Path("{userId}")
	public Response deleteUser(@PathParam("userId") long userId) {
		
		System.out.println("userID ==> " + userId);
		
		 List<User> found = UserService.users.stream().filter(
                 x -> userId == x.getCodigo()).collect(Collectors.toList());


		 //Throws error in case of the user not found
		 if(found.isEmpty()) 
		                    return Response.status(Status.BAD_REQUEST).entity("User not found").build();
		 
		 User updateUser = found.get(0);
		 UserService.users.remove(updateUser);
		 return Response.noContent().build();
	}
		 
		 
	@HEAD
	public Response pingUsersService() {
		
		return Response.noContent().header("running", true).build();
	
	}

}
