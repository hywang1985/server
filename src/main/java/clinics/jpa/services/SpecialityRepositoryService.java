package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Speciality;
import clinics.jpa.repository.SpecialityRepository;
import clinics.utils.Constants;

@Service
public class SpecialityRepositoryService extends AbstractRepositoryService<SpecialityRepository, Speciality, Integer> {

	@Autowired
	private SpecialityRepository repository;

	@Override
	public SpecialityRepository repository() {
		return repository;
	}

	public List<Speciality> findByName(String name) {
		return repository().findAllByNameLike(Constants.PERCENT + name.toUpperCase() + Constants.PERCENT);
	}
}
