package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Visit;
import clinics.jpa.repository.VisitRepository;

@Service
public class VisitRepositoryService extends AbstractRepositoryService<VisitRepository, Visit, Integer> {

	@Autowired
	private VisitRepository repository;

	@Override
	public VisitRepository repository() {
		return repository;
	}

	public List<Visit> findByPatientId(Integer patientId) {
		return repository().findByPatientId(patientId);
	}
}
