package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Appointment;
import clinics.jpa.services.AppointmentRepositoryService;
import clinics.model.AppointmentModel;
import clinics.transformer.AppointmentTransformer;

@Service
public class AppointmentService extends AbstractServiceImpl<Integer, AppointmentModel, Appointment, AppointmentRepositoryService, AppointmentTransformer> {

	@Autowired
	private AppointmentRepositoryService appointmentRepositoryService;

	@Autowired
	private AppointmentTransformer appointmentTransformer;

	@Override
	protected AppointmentRepositoryService repoService() {
		return appointmentRepositoryService;
	}

	@Override
	protected AppointmentTransformer transformer() {
		return appointmentTransformer;
	}

	@Override
	public AppointmentModel save(AppointmentModel resource) {
		return super.save(resource);
	}

	public List<AppointmentModel> getAll() {
		return super.getAll();
	}
}
