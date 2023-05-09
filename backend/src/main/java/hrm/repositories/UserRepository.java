package hrm.repositories;

import hrm.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
   User findByEmailAndPasswordAndType(String email,String password,String type);
   User findByEmailAndPassword(String email,String password);
}