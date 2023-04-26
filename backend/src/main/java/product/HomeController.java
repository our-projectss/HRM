package product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import hrm.repositories.UserRepository;
import hrm.models.User;

@RestController
@RequestMapping("/users")
public class HomeController {
	@GetMapping("")
	public List<String> home() {

		UserRepository.save(new User("1", "asd", "asd"));

		return List.of("hello 1", "hello 2");
	}

}
