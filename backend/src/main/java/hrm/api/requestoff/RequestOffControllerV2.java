package hrm.api.requestoff;

import hrm.entity.RequestOff;
import hrm.repositories.RequestOffRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/request-off")
@CrossOrigin(origins = "*")
public class RequestOffControllerV2 {
    private RequestOffRepository requestOffRepository;

    public RequestOffControllerV2(RequestOffRepository requestOffRepository) {
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
    public Map<String, String> updateRequestOff(@PathVariable Long id, @RequestBody RequestOff requestoff) {
        requestOffRepository.save(requestoff);
        return Collections.singletonMap("message", "Edit " + id + " success");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteRequestOff(@PathVariable Long id) {
        RequestOff requestoff = requestOffRepository.findById(id).get();
        requestOffRepository.delete(requestoff);
        return Collections.singletonMap("message", "Delete " + id + " success");
    }
}
