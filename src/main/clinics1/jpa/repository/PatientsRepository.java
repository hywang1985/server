package clinics.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import clinics.entity.Patient;

@Repository
public interface PatientsRepository extends BaseRepository<Patient, Integer> {
	
	public Page<Patient> findAllByFirstNameLikeOrLastNameLike(String fname, String lname, Pageable pageRequest);

}