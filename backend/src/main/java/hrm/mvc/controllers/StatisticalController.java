package hrm.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import hrm.entity.User;
import hrm.mvc.services.AppService;
import hrm.repositories.UserRepository;

@Controller
public class StatisticalController {
	private UserRepository userRepository;
    private AppService appService = new AppService();

    public StatisticalController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/statistical")
    public String getStatisticalView(Model model) {
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
        return "statistical";
    }
}
