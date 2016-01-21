package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Medication;
import clinics.entity.Patient;
import clinics.entity.Visit;
import clinics.jpa.repository.MedicationRepository;

@Service
public class MedicationRepositoryService extends AbstractRepositoryService<MedicationRepository, Medication, Integer> {

	@Autowired
	private MedicationRepository repository;

	@Override
	public MedicationRepository repository() {
		return repository;
	}

	public List<Medication> getPatientVisitMedications(Patient patient, Visit visit) {
		return repository().findAllByPatientAndVisit(patient, visit);
	}
}
