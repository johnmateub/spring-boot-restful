package co.mateusbello.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.mateusbello.exception.UserExistsException;
import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.User;
import co.mateusbello.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User userDb = userRepository.findByUsername(user.getUsername());
		if (userDb != null) {
			throw new UserExistsException("User " + user.getUsername() + " already exists.");
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not Found");
		}
		return user;
	}
	
	public User updateUserById(User user, Long id) throws UserNotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty()) {
			throw new UserNotFoundException("User not Found");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not Found");
		}
		userRepository.delete(user.get());
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
