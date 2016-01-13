package clinics.transformer;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Patient;
import clinics.model.PatientModel;

@Component
public class PatientTransformer extends AbstractDTOTransformer<PatientModel, Patient> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public Patient transformFrom(PatientModel source) {
		Patient dest = null;
		if (source != null) {
			try {
				dest = new Patient();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				if(dest.getId() == null) {
					dest.setCreatedDate(new Date());
				} else {
					dest.setModifiedDate(new Date());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public PatientModel transformTo(Patient source) {
		PatientModel dest = null;
		if (source != null) {
			try {
				dest = new PatientModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}