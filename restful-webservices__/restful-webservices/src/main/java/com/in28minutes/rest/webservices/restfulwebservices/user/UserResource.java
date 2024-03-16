package com.in28minutes.rest.webservices.restfulwebservices.user;
import java.net.URI;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
@RestController
public class UserResource {

	private UserDaoService service;

	private static final Logger LOGGER = LogManager.getLogger(UserResource.class);
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	@GetMapping("/users")
	public List<User> retrieveAllUsers(@RequestParam(name = "sortBy", required = false) String sortBy) {
		LOGGER.info("Received request to retrieve all users. Sort by: {}", sortBy);
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retriveAllUser(@PathVariable int id){
	  LOGGER.info("Request reached to get user detials from UserResource.java  for USER ID : " + id);
	   User user = service.findOne(id);
	   
	    if(user==null) 
	     	throw new UserNotFoundException("id:" +id);
	LOGGER.info("User returing for user id: " + id);
	    return user;	
	   //return service.findAll();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}	
	@PostMapping("/users") 
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		LOGGER.info(" pOST Mapping" + user);
		User savedUser = service.save(user);
		LOGGER.info("User created and coming back to User Resource.java End point");
	 URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
			          .path("/{id}")
			          .buildAndExpand(savedUser.getId())
			          .toUri();
	 return ResponseEntity.created(location).build();
	}
}
