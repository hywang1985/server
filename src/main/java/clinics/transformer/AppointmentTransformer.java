package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Appointment;
import clinics.jpa.services.DepartmentRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.model.AppointmentModel;

@Component
public class AppointmentTransformer extends AbstractDTOTransformer<AppointmentModel, Appointment> {

	private static final String[] FROM_EXCLUDES = new String[] { "department", "doctor", "patient", "done" };
	private static final String[] TO_EXCLUDES = new String[] { "department", "doctor", "patient", "done" };

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private DepartmentRepositoryService departmentRepositoryService;

	@Override
	public Appointment transformFrom(AppointmentModel source) {
		Appointment dest = null;
		if (source != null) {
			try {
				dest = new Appointment();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
				if (null != source.getPatient()) {
					dest.setPatient(patientRepositoryService.findOne(source.getPatient()));
				}
				if (null != source.getDoctor()) {
					dest.setDoctor(staffRepositoryService.findOne(source.getDoctor()));
				}
				if (null != source.getDepartment()) {
					dest.setDepartment(departmentRepositoryService.findOne(source.getDepartment()));
				}
				if(null == source.getId()) {
					dest.setDone(false);
				} else {
					dest.setDone(source.getDone() == null ? false : source.getDone());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public AppointmentModel transformTo(Appointment source) {
		AppointmentModel dest = null;
		if (source != null) {
			try {
				dest = new AppointmentModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
				if (null != source.getPatient()) {
					dest.setPatient(source.getPatient().getId());
				}
				if (null != source.getDoctor()) {
					dest.setDoctor(source.getDoctor().getId());
				}
				if (null != source.getDepartment()) {
					dest.setDepartment(source.getDepartment().getId());
				}
				dest.setDone(source.getDone() == null ? false : source.getDone());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}