package com.saraya.testRestApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(HelloTestController.class)
public class HelloTestControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Test
	public void helloSarayaTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/saraya")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("Hello Saraya people", result.getResponse().getContentAsString());
		
	}

}
