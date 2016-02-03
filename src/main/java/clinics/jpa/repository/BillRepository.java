package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Bill;
import clinics.entity.Patient;
import clinics.entity.Visit;

@Repository
public interface BillRepository extends BaseRepository<Bill, Integer> {

	List<Bill> findAllByPatientAndVisit(Patient patient, Visit visit);

	Bill findById(Integer id);

}
