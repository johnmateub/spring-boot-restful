package co.mateusbello.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.dto.UserMmDto;
import co.mateusbello.exception.UserNotFoundException;
import co.mateusbello.model.User;
import co.mateusbello.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOpt = userService.getUserById(id);
		if(userOpt.isEmpty()) {
			throw new UserNotFoundException("Usr not Found");
		}
		
		User user = userOpt.get();
		UserMmDto userDto = modelMapper.map(user, UserMmDto.class);
		return userDto;
	}
}
