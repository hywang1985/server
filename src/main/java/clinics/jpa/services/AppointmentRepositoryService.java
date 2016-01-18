package clinics.jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Appointment;
import clinics.jpa.repository.AppointmentRepository;

@Service
public class AppointmentRepositoryService extends AbstractRepositoryService<AppointmentRepository, Appointment, Integer> {

	@Autowired
	private AppointmentRepository repository;

	@Override
	public AppointmentRepository repository() {
		return repository;
	}
}
