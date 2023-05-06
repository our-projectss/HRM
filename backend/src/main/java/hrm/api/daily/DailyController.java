package hrm.api.daily;

import hrm.api.daily.models.DailyRequest;
import hrm.entity.Daily;
import hrm.repositories.DailyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/daily")
@CrossOrigin(origins = "*")
public class DailyController {
    private DailyRepository dailyRepository;

    public DailyController(DailyRepository dailyRepository) {
        this.dailyRepository = dailyRepository;
    }

    @GetMapping
    public Iterable<Daily> getDaily(@RequestBody DailyRequest dailyRequest) {
        if (dailyRequest.hasUserId()) {
            if (dailyRequest.hasTime()) {
                return dailyRepository.findByUserIdAndDayBetween(
                        dailyRequest.getUserId(),
                        dailyRequest.getStartTime(),
                        dailyRequest.getEndTime()
                );
            }
            return dailyRepository.findByUserId(dailyRequest.getUserId());
        }

        if (dailyRequest.hasTime()) {
            return dailyRepository.findByDayBetween(
                    dailyRequest.getStartTime(),
                    dailyRequest.getEndTime()
            );
        }

        return dailyRepository.findAll();
    }

    @GetMapping({"/{id}"})
    public Daily getDailyDetail(@PathVariable Long id) {
        return dailyRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Daily createRequestOff(@RequestBody Daily daily) {
        return dailyRepository.save(daily);
    }

    @PutMapping("/{id}")
    public Map<String, String> updateRequestOff(@PathVariable Long id, @RequestBody Daily daily) {
        dailyRepository.save(daily);
        return Collections.singletonMap("message", "Edit " + id + " success");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteRequestOff(@PathVariable Long id) {
        Daily daily = dailyRepository.findById(id).get();
        dailyRepository.delete(daily);
        return Collections.singletonMap("message", "Delete " + id + " success");
    }
}
