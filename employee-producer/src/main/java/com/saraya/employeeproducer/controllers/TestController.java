package com.saraya.employeeproducer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saraya.employeeproducer.model.Employee;

@RestController
public class TestController {
	
	@RequestMapping(value="/employee", method = RequestMethod.GET)
	public Employee firstPage() {
		
		Employee emp = new Employee();
		emp.setEmpId("Emp12");
		emp.setName("Ibrahima");
		emp.setDesignation("Manager");
		emp.setSalary(3000);
		
		return emp;
		
	}

}
