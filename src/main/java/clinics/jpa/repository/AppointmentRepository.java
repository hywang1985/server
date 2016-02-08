package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Appointment;
import clinics.entity.Patient;
import clinics.entity.Staff;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Integer> {

	List<Appointment> findAllByDoctorAndDateTimeContaining(Staff staff, String date);

	List<Appointment> findAllByPatient(Patient patient);

}
