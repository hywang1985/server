package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Medication;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.MedicationModel;

@Component
public class MedicationTransformer extends AbstractDTOTransformer<MedicationModel, Medication> {

	private static final String[] FROM_EXCLUDES = new String[] { "visit", "doctor", "patient" };
	private static final String[] TO_EXCLUDES = new String[] { "visit", "doctor", "patient" };

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Override
	public Medication transformFrom(MedicationModel source) {
		Medication dest = null;
		if (source != null) {
			try {
				dest = new Medication();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
				if (null != source.getPatient()) {
					dest.setPatient(patientRepositoryService.findOne(source.getPatient()));
				}
				if (null != source.getDoctor()) {
					dest.setDoctor(staffRepositoryService.findOne(source.getDoctor()));
				}
				if (null != source.getVisit()) {
					dest.setVisit(visitRepositoryService.findOne(source.getVisit()));
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public MedicationModel transformTo(Medication source) {
		MedicationModel dest = null;
		if (source != null) {
			try {
				dest = new MedicationModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
				if (null != source.getPatient()) {
					dest.setPatient(source.getPatient().getId());
				}
				if (null != source.getDoctor()) {
					dest.setDoctor(source.getDoctor().getId());
				}
				if (null != source.getVisit()) {
					dest.setVisit(source.getVisit().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}