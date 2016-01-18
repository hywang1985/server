package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Department;
import clinics.jpa.repository.DepartmentRepository;
import clinics.utils.Constants;

@Service
public class DepartmentRepositoryService extends AbstractRepositoryService<DepartmentRepository, Department, Integer> {

	@Autowired
	private DepartmentRepository repository;

	@Override
	public DepartmentRepository repository() {
		return repository;
	}

	public List<Department> findByName(String name) {
		return repository().findAllByNameLike(Constants.PERCENT + name.toUpperCase() + Constants.PERCENT);
	}

	public List<Department> findAllAppointmentable(boolean b) {
		return repository().findAllByAppointmentable(b);
	}
}
