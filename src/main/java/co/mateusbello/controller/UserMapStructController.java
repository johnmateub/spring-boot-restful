package co.mateusbello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.mateusbello.dto.UserMsDto;
import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.mapper.UserMapper;
import co.mateusbello.model.User;
import co.mateusbello.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;

	
	@GetMapping
	public List<UserMsDto> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return userMapper.usersToUsersDtos(users);
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userMapper.userToUserMsDto(userService.getUserById(id).get());
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
}
