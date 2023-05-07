package hrm.mvc.models;

import hrm.entity.RequestOff;
import hrm.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOffUser {
	private RequestOff requestOff;
    private User user;

    public RequestOffUser() {
        super();
    }

    public RequestOffUser(RequestOff requestOff, User user) {
        super();
        this.requestOff = requestOff;
        this.user = user;
    }
   
}
