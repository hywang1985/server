package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Appointment;
import clinics.entity.Staff;
import clinics.jpa.repository.AppointmentRepository;

@Service
public class AppointmentRepositoryService extends AbstractRepositoryService<AppointmentRepository, Appointment, Integer> {

	@Autowired
	private AppointmentRepository repository;

	@Override
	public AppointmentRepository repository() {
		return repository;
	}

	public List<Appointment> getStaffAppointments(Staff staff, String date) {
		return repository().findAllByDoctorAndDateTimeContaining(staff, date);
	}
}
