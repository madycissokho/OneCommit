package com.saraya.rest.webServices.restfullwebServices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserService {
	@Autowired
	private PersonDaoService service;
	
	@Autowired
	 private UserDaoService userDaoService;
	
//	@GetMapping("/persons")
//	 public List<Person> retrieveAllPersons(){
//		 return service.findAll();
//	 }
//	
//	@GetMapping("/persons/{id}")
//	 public Person retrievePerson(@PathVariable int id) {
//		 return service.findOne(id);
//		 
//	 }
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return  userDaoService.findAll();
	}
	
	 @GetMapping("/users/{id}")
	 public EntityModel<User> retrieveUser(@PathVariable int id) {
		 User user = userDaoService.findOne(id);
		 
		 if(user.getId()==null) 
			 throw new UserNotFoundException("id-"+ id);
		 
		//"all-users", SERVER_PATH + "/users"
			//retrieveAllUsers
			EntityModel<User> resource = EntityModel.of(user);
			
			WebMvcLinkBuilder linkTo = 
					linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
		 resource.add(linkTo.withRel("all-users"));
		 
		 return resource;
	 }
	 
	 @DeleteMapping("/users/{id}")
	 public void deleteUser(@PathVariable int id) {
		 User user = userDaoService.deleteById(id);
		 
		 if(user==null) 
			 throw new UserNotFoundException("id-"+ id);
		 
		 
	 }

	 
	 @PostMapping("/users")
	 public ResponseEntity<Object> createdUser(@Valid @RequestBody User user) {
		 User savedUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder
		 .fromCurrentRequest()
		 .path("/{id}")
		 .buildAndExpand(savedUser.getId()).toUri();
		 
	 return ResponseEntity.created(location).build();
	
	 }


}
