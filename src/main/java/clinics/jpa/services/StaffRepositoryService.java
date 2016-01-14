package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Staff;
import clinics.jpa.repository.StaffsRepository;

@Service
public class StaffRepositoryService extends AbstractRepositoryService<StaffsRepository, Staff, Integer> {

	public static final String PERCENT = "%";

	@Autowired
	private StaffsRepository repository;

	@Override
	public StaffsRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<Staff> findAll() {
		return super.findAll();
	}

	@Override
	public Staff findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public Staff save(Staff entity) {
		return super.save(entity);
	}

	public Staff findById(Integer id) {
		return repository().findById(id);
	}
}
