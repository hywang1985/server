package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import clinics.entity.Patient;
import clinics.jpa.repository.PatientsRepository;

@Service("patientsRepositoryService")
public class PatientRepositoryService extends AbstractRepositoryService<PatientsRepository, Patient, Integer> {

	public static final String PERCENT = "%";

	@Autowired
	private PatientsRepository repository;

	@Override
	public PatientsRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<Patient> findAll() {
		return super.findAll();
	}

	@Override
	public Patient findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public Patient save(Patient entity) {
		return super.save(entity);
	}

	public Page<Patient> findAll(PageRequest pageRequest) {
		return repository().findAll(pageRequest);
	}
}
