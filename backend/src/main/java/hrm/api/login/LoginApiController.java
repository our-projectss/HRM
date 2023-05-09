package hrm.api.login;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrm.entity.User;
import hrm.mvc.models.Login;
import hrm.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/login", produces = "application/json")
@CrossOrigin(origins = "*")
public class LoginApiController {
	private UserRepository userRepository;
	public LoginApiController (UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	@PostMapping
	public User loginUser(@RequestBody Login login) {
		String email=login.getEmail();
		String password=login.getPassword();
		return userRepository.findByEmailAndPassword(email, password);
		
	}
}
