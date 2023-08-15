package co.mateusbello.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.Order;
import co.mateusbello.model.User;
import co.mateusbello.service.UserService;

@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable("userId") Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		List<Order> orders = userOpt.get().getOrders();
		return CollectionModel.of(orders);
	}
	
}
