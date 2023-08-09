package co.mateusbello.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.mateusbello.exception.OrderNotFoundException;
import co.mateusbello.model.Order;
import co.mateusbello.model.User;
import co.mateusbello.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public Order createOrder(User user, Order order) {
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	public Optional<Order> getOrderById(Long id) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			throw new OrderNotFoundException("Order not Found");
		}
		return order;
	}
	
	
}
