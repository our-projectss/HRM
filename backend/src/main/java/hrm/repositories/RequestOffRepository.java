package hrm.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hrm.entity.Daily;
import hrm.entity.RequestOff;
import hrm.entity.User;
import org.springframework.stereotype.Repository;

public interface RequestOffRepository extends CrudRepository<RequestOff, Long> {
	List<RequestOff> findByUserId(Long userId);

	List<RequestOff> findByUserIdAndDayOffBetween(Long userId, Date startTime, Date endTime);

	List<RequestOff> findByDayOffBetween(Date startTime, Date endTime);
}
