package hrm.mvc.controllers;

import hrm.entity.User;
import hrm.mvc.services.AppService;
import hrm.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DashboardController {
    private UserRepository userRepository;
    private AppService appService = new AppService();

    public DashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getDashboardView(Model model) {
        Long currentAdminId = this.appService.getCurrentAdminId();
        if (currentAdminId == null) {
            return "redirect:/login";
        }

        User user = userRepository.findById(currentAdminId).get();
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("currentUser",user);
        return "home";
    }
  	@GetMapping("/delete/{id}")
  	public String deleteUser(@PathVariable Long id) {
  		userRepository.deleteById(id);
  		return "redirect:/";
  	}
	
}
