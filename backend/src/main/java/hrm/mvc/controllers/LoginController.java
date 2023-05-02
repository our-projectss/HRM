package hrm.mvc.controllers;

import hrm.entity.User;
import hrm.mvc.models.Login;
import hrm.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class LoginController {
    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") Login login, Model m) {
        String email = login.getEmail();
        String pass = login.getPassword();

        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            return "home";
        }
        m.addAttribute("error", "Incorrect Email or Password!");
        return "login";
    }
}
