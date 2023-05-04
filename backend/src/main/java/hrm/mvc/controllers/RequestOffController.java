package hrm.mvc.controllers;
import hrm.entity.User;
import hrm.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import hrm.mvc.services.AppService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestOffController {
//	private UserRepository userRepository;
//	private AppService appService = new AppService();
//	
//	public RequestOffController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
//	@GetMapping("/request-off")
//	public String getLogin() {
//        Long currentAdminId = this.appService.getCurrentAdminId();
//        if (currentAdminId == null) {
//            return "redirect:/login";
//        }
//
//        User user = userRepository.findById(currentAdminId).get();
//        if (user == null) {
//            return "redirect:/login";
//        }
//        return "request-off";
//	}
	
//	@GetMapping("/request-off")
//	public String getRequest() {
//		
//		return "request-off";
//	}
	
//	//@PostMapping("/request-off")
//	public String request() {
//		String id
//	}
	
	
}
