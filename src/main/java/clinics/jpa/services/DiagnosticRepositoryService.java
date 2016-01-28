package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Diagnostic;
import clinics.entity.Patient;
import clinics.entity.Visit;
import clinics.jpa.repository.DiagnosticRepository;

@Service
public class DiagnosticRepositoryService extends AbstractRepositoryService<DiagnosticRepository, Diagnostic, Integer> {

	@Autowired
	private DiagnosticRepository repository;

	@Override
	public DiagnosticRepository repository() {
		return repository;
	}

	public List<Diagnostic> getPatientVisitDiagnostics(Patient patient, Visit visit) {
		return repository().findAllByPatientAndVisit(patient, visit);
	}

	public Diagnostic findById(Integer id) {
		return repository().findById(id);
	}
}
