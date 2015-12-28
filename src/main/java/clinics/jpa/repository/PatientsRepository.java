package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Patient;

@Repository
public interface PatientsRepository extends BaseRepository<Patient, Integer> {

}