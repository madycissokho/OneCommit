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
public class PersonService {
	@Autowired
	private PersonDaoService service;
	
	
	@GetMapping("/persons")
	 public List<Person> retrieveAllPersons(){
		 return service.findAll();
	 }
	
	
	
	 @GetMapping("/persons/{id}")
	 public EntityModel<Person> retrieveById(@PathVariable int id) {
		 Person person = service.findOne(id);
		 
		 if(person.getId()==null) 
			 throw new UserNotFoundException("id-"+ id);
		 
		//"all-users", SERVER_PATH + "/users"
			//retrieveAllUsers
			EntityModel<Person> resource = EntityModel.of(person);
			
			WebMvcLinkBuilder linkTo = 
					linkTo(methodOn(this.getClass()).retrieveAllPersons());
			
		 resource.add(linkTo.withRel("all-persons"));
		 
		 return resource;
	 }
	 
	 @DeleteMapping("/persons/{id}")
	 public void deletePerson(@PathVariable int id) {
		 Person person = service.deleteById(id);
		 
		 if(person==null) 
			 throw new UserNotFoundException("id-"+ id);
		 
		 
	 }

	 
	 @PostMapping("/persons")
	 public ResponseEntity<Object> createdPerson(@Valid @RequestBody Person person) {
		 Person savedPerson = service.save(person);
		
		URI location = ServletUriComponentsBuilder
		 .fromCurrentRequest()
		 .path("/{id}")
		 .buildAndExpand(savedPerson.getId()).toUri();
		 
	 return ResponseEntity.created(location).build();
	
	 }


}
