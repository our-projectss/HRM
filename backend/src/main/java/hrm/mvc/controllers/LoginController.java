package hrm.mvc.controllers;

import hrm.entity.User;
import hrm.mvc.models.Login;
import hrm.mvc.services.AppService;
import hrm.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class LoginController {
    private UserRepository userRepository;
    private AppService appService = new AppService();

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") Login login, Model m, RedirectAttributes redirectAttributes) {
        String email = login.getEmail();
        String pass = login.getPassword();
        User user = userRepository.findByEmailAndPasswordAndType(email, pass, "admin");
        if (user != null) {
            this.appService.setCurrentAdminId((Long)user.getId());
            return "redirect:/";
        }
        m.addAttribute("error", "Incorrect Email or Password!");
        return "login";
    }
}
