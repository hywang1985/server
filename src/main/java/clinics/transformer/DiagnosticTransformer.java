package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Diagnostic;
import clinics.jpa.services.DiagnosticRepositoryService;
import clinics.jpa.services.DiagnosticTypeRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.DiagnosticModel;

@Component
public class DiagnosticTransformer extends AbstractDTOTransformer<DiagnosticModel, Diagnostic> {

	private static final String[] FROM_EXCLUDES = new String[] { "visit", "doctor", "patient", "type" };
	private static final String[] TO_EXCLUDES = new String[] { "visit", "doctor", "patient", "type" };

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Autowired
	private DiagnosticTypeRepositoryService diagnosticTypeRepositoryService;

	@Autowired
	private DiagnosticRepositoryService diagnosticRepositoryService;

	@Override
	public Diagnostic transformFrom(DiagnosticModel source) {
		Diagnostic dest = null;
		if (source != null) {
			try {
				dest = new Diagnostic();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setTest(dest.getTest().toUpperCase());
				if (null == source.getId()) {
					if (null != source.getPatient()) {
						dest.setPatient(patientRepositoryService.findOne(source.getPatient()));
					}
					if (null != source.getDoctor()) {
						dest.setDoctor(staffRepositoryService.findById(source.getDoctor()));
					}
					if (null != source.getVisit()) {
						dest.setVisit(visitRepositoryService.findOne(source.getVisit()));
					}
					if (null != source.getType()) {
						dest.setType(diagnosticTypeRepositoryService.findOne(source.getType()));
					}
				} else {
					Diagnostic fromDb = diagnosticRepositoryService.findById(source.getId());
					if (null != fromDb) {
						dest.setPatient(fromDb.getPatient());
						dest.setDoctor(fromDb.getDoctor());
						dest.setVisit(fromDb.getVisit());
						dest.setType(fromDb.getType());
					}
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public DiagnosticModel transformTo(Diagnostic source) {
		DiagnosticModel dest = null;
		if (source != null) {
			try {
				dest = new DiagnosticModel();
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
				if (null != source.getType()) {
					dest.setType(source.getType().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}