package ir.itstar.quickPoll.repository;

import org.springframework.data.repository.CrudRepository;

import ir.itstar.quickPoll.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
}
