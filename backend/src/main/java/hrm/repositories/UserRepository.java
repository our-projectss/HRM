package hrm.repositories;

import org.springframework.data.repository.CrudRepository;
import hrm.models.User;


public interface UserRepository extends CrudRepository<User, String> {
	
}
