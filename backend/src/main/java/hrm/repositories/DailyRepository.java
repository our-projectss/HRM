package hrm.repositories;

import hrm.entity.Daily;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface DailyRepository extends CrudRepository<Daily, Long> {
  List<Daily> findByUserId(Long userId);

  List<Daily> findByUserIdAndDayBetween(Long userId, Date startTime, Date endTime);

  List<Daily> findByDayBetween(Date startTime, Date endTime);

}
