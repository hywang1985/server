package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Appointment;
import clinics.entity.Staff;
import clinics.jpa.services.AppointmentRepositoryService;
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
}
