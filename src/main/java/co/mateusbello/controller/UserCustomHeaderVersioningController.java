package co.mateusbello.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.dto.UserDtoV1;
import co.mateusbello.dto.UserDtoV2;
import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.User;
import co.mateusbello.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/versioning/params/users")
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = "/{id}", params = "version=1")
	public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(id);
		if(userOpt.isEmpty()) {
			throw new UserNotFoundException("Usr not Found");
		}
		
		User user = userOpt.get();
		UserDtoV1 userDto = modelMapper.map(user, UserDtoV1.class);
		return userDto;
	}
	
	@GetMapping(value = "/{id}", params = "version=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(id);
		if(userOpt.isEmpty()) {
			throw new UserNotFoundException("Usr not Found");
		}
		
		User user = userOpt.get();
		UserDtoV2 userDto = modelMapper.map(user, UserDtoV2.class);
		return userDto;
	}
}
