package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Medication;
import clinics.entity.Patient;
import clinics.entity.Visit;

@Repository
public interface MedicationRepository extends BaseRepository<Medication, Integer> {

	List<Medication> findAllByPatientAndVisit(Patient patient, Visit visit);

	Medication findById(Integer id);

}
