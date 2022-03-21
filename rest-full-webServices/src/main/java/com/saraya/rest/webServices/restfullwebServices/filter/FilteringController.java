package com.saraya.rest.webServices.restfullwebServices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		
		return someBean;
	}
	

	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList( new SomeBean("value1","value2","value3"),
				new SomeBean("value21","value22","value23"));
	}

}
