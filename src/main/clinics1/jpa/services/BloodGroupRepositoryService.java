package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.BloodGroup;
import clinics.jpa.repository.BloodGroupRepository;

@Service
public class BloodGroupRepositoryService extends AbstractRepositoryService<BloodGroupRepository, BloodGroup, Integer> {

	@Autowired
	private BloodGroupRepository repository;

	@Override
	public BloodGroupRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<BloodGroup> findAll() {
		return super.findAll();
	}

	@Override
	public BloodGroup findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public BloodGroup save(BloodGroup entity) {
		return super.save(entity);
	}

	public BloodGroup findByName(String name) {
		return repository().findByName(name);
	}
}
