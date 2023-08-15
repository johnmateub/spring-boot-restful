package co.mateusbello.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.Order;
import co.mateusbello.model.User;
import co.mateusbello.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> users = userService.getAllUsers();
		for(User user : users) {
			Link selfLink = WebMvcLinkBuilder.linkTo(getClass()).slash(user.getId()).withSelfRel();
			user.add(selfLink);
			
			//relationship link with getAllOrders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getId());
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		//self link for all users
		Link selfLinkAllUsers = WebMvcLinkBuilder.linkTo(getClass()).withSelfRel();
		return CollectionModel.of(users, selfLinkAllUsers);
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOpt = userService.getUserById(id);
			User user = userOpt.get();
			Link selfLink = WebMvcLinkBuilder.linkTo(getClass()).slash(user.getId()).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalUser = EntityModel.of(user);
			return finalUser;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
}
