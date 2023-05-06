package hrm.api.daily.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DailyRequest {
    public Long userId;
    public Date startTime;
    public Date endTime;

    public DailyRequest() {
        this.userId = null;
        this.startTime = null;
        this.endTime = null;
    }

    public DailyRequest(Long userId, Date startTime, Date endTime) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean hasUserId() {
        return this.userId != null;
    }

    public boolean hasTime() {
        return this.startTime != null && this.endTime != null;
    }
}
