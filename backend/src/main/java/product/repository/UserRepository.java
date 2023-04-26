package product.repository;

import org.springframework.data.repository.CrudRepository;

import product.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
