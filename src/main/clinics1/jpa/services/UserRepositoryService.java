package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.User;
import clinics.jpa.repository.UserRepository;

@Service("userRepositoryService")
public class UserRepositoryService extends AbstractRepositoryService<UserRepository, User, Integer> {

	@Autowired
	private UserRepository repository;

	@Override
	public UserRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<User> findAll() {
		return super.findAll();
	}

	@Override
	public User findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public User save(User entity) {
		return super.save(entity);
	}

	public User findByUsername(String userName) {
		return repository().findByUsername(userName);
	}

	public Integer findMaxId() {
		return repository().findMaxId();
	}
}
