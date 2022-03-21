package com.saraya.testRestApi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTestController {
	
	@RequestMapping("/saraya")
	public String helloSaraya() {
		return "Hello Saraya people";
	}

}
