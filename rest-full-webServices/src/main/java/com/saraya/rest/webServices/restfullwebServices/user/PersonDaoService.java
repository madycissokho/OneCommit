package com.saraya.rest.webServices.restfullwebServices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PersonDaoService {
	
	private static List<Person> persons = new ArrayList<>();
	
	private static int personsCount = 5;
	
	   static {
		   persons.add(new Person(1,"Aicha",new Date()));
		   persons.add(new Person(2,"Aminata",new Date()));
		   persons.add(new Person(3,"Soumaya",new Date()));
		   persons.add(new Person(4,"Assiya",new Date()));
		   persons.add(new Person(5,"Mariam",new Date()));
	   }
	   
	   public List<Person> findAll(){
		   return persons;
	   }
	   
	   public Person save(Person person) {
			   if(person.getId()==null) {
				   person.setId(++personsCount);
		   }
			   persons.add(person);
			   return person;
	   }
	   
	   public Person findOne(int id) {
		   for(Person person : persons) {
			   if(person.getId()==id) {
			   return person;
		   }
		   
	   }
	   return null;

}

	public Person deleteById(int id) {
		Iterator<Person> iterator = persons.listIterator();
		
	while(iterator.hasNext()) {
		Person person = iterator.next();
		
	if(person.getId()==id) {
		iterator.remove();
		return person;
	}
	}
		return null;
	}
}
