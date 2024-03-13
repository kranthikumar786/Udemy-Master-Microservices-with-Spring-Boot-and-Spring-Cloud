package com.in28minutes.rest.webservices.restfulwebservices.helloworld;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class HelloWorldController {

	
	//@RequestMapping(method = RequestMethod.GET,path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World 1";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
	return new HelloWorldBean("Hello World");
	}

	/*
	 *  Path Parameters
	 *    user/{id}/todos/{id} = > /users/1/todos/101
	 * */
	
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
	return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
}
