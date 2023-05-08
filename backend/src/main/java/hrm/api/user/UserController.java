package hrm.api.user;

import hrm.entity.User;
import hrm.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping({"/{id}"})
    public User getUserDetail(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Map<String, String> updateUser(@PathVariable long id, @RequestBody User user) {
        userRepository.save(user);
        return Collections.singletonMap("message", "Edit " + id + " success");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return Collections.singletonMap("message", "Delete " + id + " success");
    }
}
