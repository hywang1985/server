package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Diagnostic;
import clinics.entity.DiagnosticType;
import clinics.entity.Patient;
import clinics.entity.Staff;
import clinics.entity.Visit;
import clinics.jpa.services.DiagnosticRepositoryService;
import clinics.jpa.services.DiagnosticTypeRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.DiagnosticModel;
import clinics.transformer.DiagnosticTransformer;

@Service
public class DiagnosticService extends AbstractServiceImpl<Integer, DiagnosticModel, Diagnostic, DiagnosticRepositoryService, DiagnosticTransformer> {

	@Autowired
	private DiagnosticRepositoryService diagnosticRepositoryService;

	@Autowired
	private DiagnosticTransformer diagnosticTransformer;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Autowired
	private DiagnosticTypeRepositoryService diagnosticTypeRepositoryService;

	@Override
	protected DiagnosticRepositoryService repoService() {
		return diagnosticRepositoryService;
	}

	@Override
	protected DiagnosticTransformer transformer() {
		return diagnosticTransformer;
	}

	@Override
	public DiagnosticModel save(DiagnosticModel resource) {
		Patient patient = patientRepositoryService.findOne(resource.getPatient());
		Visit visit = visitRepositoryService.findOne(resource.getVisit());
		Staff staff = staffRepositoryService.findById(resource.getDoctor());
		DiagnosticType diagnosticType = diagnosticTypeRepositoryService.findOne(resource.getType());
		if (null != patient && null != visit && null != staff && null != diagnosticType) {
			resource = super.save(resource);
		}
		return resource;
	}

	public List<DiagnosticModel> getAll() {
		return super.getAll();
	}

	public List<DiagnosticModel> getPatientVisitDiagnostics(Integer patientId, Integer visitId) {
		Patient patient = patientRepositoryService.findOne(patientId);
		Visit visit = visitRepositoryService.findOne(visitId);
		if (null != patient && null != visit) {
			return transformer().transformTo(repoService().getPatientVisitDiagnostics(patient, visit));
		}
		return new ArrayList<DiagnosticModel>();
	}
}
