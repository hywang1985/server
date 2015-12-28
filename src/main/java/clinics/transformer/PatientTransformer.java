package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Patient;
import clinics.jpa.services.PatientRepositoryService;
import clinics.model.PatientModel;

@Component
public class PatientTransformer extends AbstractDTOTransformer<PatientModel, Patient> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Override
	public Patient transformFrom(PatientModel source) {
		Patient dest = null;
		if (source != null) {
			try {
				dest = new Patient();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
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