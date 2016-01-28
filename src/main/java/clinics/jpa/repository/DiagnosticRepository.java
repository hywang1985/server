package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Diagnostic;
import clinics.entity.Patient;
import clinics.entity.Visit;

@Repository
public interface DiagnosticRepository extends BaseRepository<Diagnostic, Integer> {

	List<Diagnostic> findAllByPatientAndVisit(Patient patient, Visit visit);

	Diagnostic findById(Integer id);

}
