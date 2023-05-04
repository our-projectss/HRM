package hrm.api.requestoff;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hrm.entity.RequestOff;
import hrm.repositories.RequestOffRepository;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/request-off", produces = "application/json")
@CrossOrigin(origins = "*")
public class RequestOffController {
	private RequestOffRepository requestOffRepository;

    public RequestOffController(RequestOffRepository requestOffRepository) {
        this.requestOffRepository = requestOffRepository;
    }
    
    @GetMapping
    public Iterable<RequestOff> getAll() {
        return requestOffRepository.findAll();
    }
	
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestOff createRequestOff(@RequestBody RequestOff requestoff) {
        return requestOffRepository.save(requestoff);
    }

    @PutMapping("/{id}")
    public Map<String, String> updateRequestOff(@PathVariable long id, @RequestBody RequestOff requestoff) {
    	requestOffRepository.save(requestoff);
        return Collections.singletonMap("message", "Edit " + id + " success");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteRequestOff(@PathVariable long id) {
    	RequestOff requestoff = requestOffRepository.findById(id).get();
        requestOffRepository.delete(requestoff);
        return Collections.singletonMap("message", "Delete " + id + " success");
    }
	
}
