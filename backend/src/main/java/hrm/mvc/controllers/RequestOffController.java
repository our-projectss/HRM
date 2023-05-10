package hrm.mvc.controllers;

import hrm.entity.User;
import hrm.entity.RequestOff;
import hrm.mvc.services.AppService;
import hrm.repositories.RequestOffRepository;
import hrm.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.sql.Date;
import hrm.mvc.models.*;


@Controller
public class RequestOffController {
	private RequestOffRepository requestOffRepository;
	private UserRepository userRepository;
	private AppService appService = new AppService();
	
	public RequestOffController(UserRepository userRepository,RequestOffRepository requestOffRepository ) {
		this.userRepository = userRepository;
		this.requestOffRepository = requestOffRepository;
		
    }
	

    @GetMapping("/request-off")
    public String getRequestOffView(Model model) {
        Long currentAdminId = this.appService.getCurrentAdminId();
        if (currentAdminId == null) {
            return "redirect:/login";
        }

        User user = userRepository.findById(currentAdminId).get();
        if (user == null) {
            return "redirect:/login";
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        
        java.util.Date utilDate = cal.getTime();
        java.sql.Date startTime = new java.sql.Date(utilDate.getTime());
        
         
        cal.add(Calendar.MONTH, 1);
        utilDate = cal.getTime();
        Date endTime = new java.sql.Date(utilDate.getTime());
                
        List <RequestOff> requestOffs = requestOffRepository.findByDayOffBetween(startTime, endTime);
        List<RequestOffUser> list=new ArrayList<RequestOffUser>();
        for(RequestOff item : requestOffs) {
            list.add(new RequestOffUser(item,userRepository.findById(item.getUserId()).get()));
        }
        model.addAttribute("requestOffUsers", list);
        model.addAttribute("currentUser",user);
        
        return "request-off";       
    }
	
    @GetMapping("/approved/{id}")
  	public String approvedRequestOff(@PathVariable Long id) {
    	RequestOff requestOff = requestOffRepository.findById(id).get();
    	requestOff.setStatus("approved");
    	requestOff.setApprovedBy(this.appService.getCurrentAdminId());
    	requestOffRepository.save(requestOff);
  		
  		return "redirect:/request-off";
  	}
    
    @GetMapping("/reject/{id}")
  	public String rejectRequestOff(@PathVariable Long id) {
    	RequestOff requestOff = requestOffRepository.findById(id).get();
    	requestOff.setStatus("reject");
    	requestOff.setApprovedBy(this.appService.getCurrentAdminId());
    	requestOffRepository.save(requestOff);
  		
  		return "redirect:/request-off";
  	}
	
}
