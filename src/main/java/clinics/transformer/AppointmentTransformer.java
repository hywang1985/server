package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Appointment;
import clinics.entity.Department;
import clinics.entity.Patient;
import clinics.entity.Staff;
import clinics.model.AppointmentModel;

@Component
public class AppointmentTransformer extends AbstractDTOTransformer<AppointmentModel, Appointment> {

	private static final String[] FROM_EXCLUDES = new String[] { "department", "doctor", "patient" };
	private static final String[] TO_EXCLUDES = new String[] { "department", "doctor", "patient" };

	@Override
	public Appointment transformFrom(AppointmentModel source) {
		Appointment dest = null;
		if (source != null) {
			try {
				dest = new Appointment();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
				if (null != source.getPatient()) {
					dest.setPatient(new Patient(source.getPatient()));
				}
				if (null != source.getDoctor()) {
					dest.setDoctor(new Staff(source.getDoctor()));
				}
				if (null != source.getDepartment()) {
					dest.setDepartment(new Department(source.getDepartment()));
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
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}