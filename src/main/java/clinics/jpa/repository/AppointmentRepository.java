package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Appointment;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Integer> {

}
