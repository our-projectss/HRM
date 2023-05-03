package hrm.mvc.services;

public class AppService {
    public static Long currentAdminId = null;

    public Long getCurrentAdminId() {
        return this.currentAdminId;
    }

    public void setCurrentAdminId(Long id) {
        this.currentAdminId = id;
    }
}
