package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.DiagnosticType;
import clinics.jpa.repository.DiagnosticTypeRepository;

@Service
public class DiagnosticTypeRepositoryService extends AbstractRepositoryService<DiagnosticTypeRepository, DiagnosticType, Integer> {

	@Autowired
	private DiagnosticTypeRepository repository;

	@Override
	public DiagnosticTypeRepository repository() {
		return repository;
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public List<DiagnosticType> findAll() {
		return super.findAll();
	}

	@Override
	public DiagnosticType findOne(Integer id) {
		return super.findOne(id);
	}

	@Override
	public DiagnosticType save(DiagnosticType entity) {
		return super.save(entity);
	}

	public List<DiagnosticType> findByName(String name) {
		return repository().findByName(name);
	}
}
