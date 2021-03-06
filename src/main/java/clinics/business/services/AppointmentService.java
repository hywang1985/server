package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Appointment;
import clinics.entity.Patient;
import clinics.entity.Staff;
import clinics.jpa.services.AppointmentRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.model.AppointmentModel;
import clinics.transformer.AppointmentTransformer;

@Service
public class AppointmentService extends AbstractServiceImpl<Integer, AppointmentModel, Appointment, AppointmentRepositoryService, AppointmentTransformer> {

	@Autowired
	private AppointmentRepositoryService appointmentRepositoryService;

	@Autowired
	private AppointmentTransformer appointmentTransformer;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private PatientRepositoryService patientRepositoryService;

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

	public List<AppointmentModel> getStaffAppointments(Integer staffId, String date) {
		Staff staff = staffRepositoryService.findById(staffId);
		if (null != staff) {
			return transformer().transformTo(repoService().getStaffAppointments(staff, date));
		}
		return new ArrayList<AppointmentModel>();
	}

	public AppointmentModel done(AppointmentModel model) {
		if (null != model && null != model.getId()) {
			Appointment fromDb = repoService().findOne(model.getId());
			if (null != fromDb) {
				fromDb.setDone(true);
				repoService().save(fromDb);
				model.setDone(true);
			}
		}
		return model;
	}

	public List<AppointmentModel> getPatientAppointments(Integer patientId) {
		Patient patient = patientRepositoryService.findOne(patientId);
		if (null != patient) {
			return transformer().transformTo(repoService().getPatientAppointments(patient));
		}
		return new ArrayList<AppointmentModel>();
	}
}
