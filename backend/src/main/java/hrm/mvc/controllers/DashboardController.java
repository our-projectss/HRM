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
        model.addAttribute("users2", userRepository.findAll());
        model.addAttribute("currentUser", user);
				model.addAttribute("userForm", new User());
        return "home";
    }
  	@GetMapping("/delete/{id}")
  	public String deleteUser(@PathVariable Long id) {
  		userRepository.deleteById(id);
  		return "redirect:/";
  	}

	@GetMapping("/logout")
	public String logoutUser() {
		this.appService.setCurrentAdminId(null);
		return "redirect:/login";
	}

  	@PostMapping("/create-user")
	public String saveStudent(@ModelAttribute("user") User user) {
		userRepository.save(user);
		return "redirect:/";
	}
  	@PostMapping("/update-user/{id}")
  	public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
  		user.setId(id);
  		System.out.println(user);
  		userRepository.save(user);
  		return "redirect:/";
  	}
  	@GetMapping("/user-detail/{id}")
  	public String detailUser(@PathVariable Long id,Model model ) {
  		Long currentAdminId = this.appService.getCurrentAdminId();
  		User user = userRepository.findById(currentAdminId).get();
  		User userid=userRepository.findById(id).get();
  		model.addAttribute("currentUser",user);
  		model.addAttribute("userid",userid);
  		return "user-detail";
  	}
 }
	

