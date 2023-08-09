package co.mateusbello.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.mateusbello.exception.OrderNotFoundException;
import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.Order;
import co.mateusbello.model.User;
import co.mateusbello.service.OrderService;
import co.mateusbello.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable("userId") Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		return userOpt.get().getOrders();
	}
	
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable("userId") Long userId, 
			@RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(userId);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		return orderService.createOrder(userOpt.get(), order);
		
	}
	
	@GetMapping("/{userId}/orders/{id}")
	public Optional<Order> getOrderById(@PathVariable("userId") Long userId, 
			@PathVariable("id") Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(userId);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		try {
			return orderService.getOrderById(id);
		} catch (OrderNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
}
