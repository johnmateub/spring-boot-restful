package co.mateusbello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.model.UserDetails;

@RestController
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("John", "Mateus", "Cajicá");
	}
}
