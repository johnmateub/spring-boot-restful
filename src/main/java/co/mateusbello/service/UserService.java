package co.mateusbello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.mateusbello.model.User;
import co.mateusbello.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	public User updateUserById(User user, Long id) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
		
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
