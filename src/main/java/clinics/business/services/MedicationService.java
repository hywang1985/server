package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Medication;
import clinics.entity.Patient;
import clinics.entity.Staff;
import clinics.entity.Visit;
import clinics.jpa.services.MedicationRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.StaffRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.MedicationModel;
import clinics.transformer.MedicationTransformer;

@Service
public class MedicationService extends AbstractServiceImpl<Integer, MedicationModel, Medication, MedicationRepositoryService, MedicationTransformer> {

	@Autowired
	private MedicationRepositoryService medicationRepositoryService;

	@Autowired
	private MedicationTransformer medicationTransformer;

	@Autowired
	private StaffRepositoryService staffRepositoryService;

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Override
	protected MedicationRepositoryService repoService() {
		return medicationRepositoryService;
	}

	@Override
	protected MedicationTransformer transformer() {
		return medicationTransformer;
	}

	@Override
	public MedicationModel save(MedicationModel resource) {
		Patient patient = patientRepositoryService.findOne(resource.getPatient());
		Visit visit = visitRepositoryService.findOne(resource.getVisit());
		Staff staff = staffRepositoryService.findById(resource.getDoctor());
		if (null != patient && null != visit && null != staff) {
			resource = super.save(resource);
		}
		return resource;
	}

	public List<MedicationModel> getAll() {
		return super.getAll();
	}

	public List<MedicationModel> getPatientVisitMedications(Integer patientId, Integer visitId) {
		Patient patient = patientRepositoryService.findOne(patientId);
		Visit visit = visitRepositoryService.findOne(visitId);
		if (null != patient && null != visit) {
			return transformer().transformTo(repoService().getPatientVisitMedications(patient, visit));
		}
		return new ArrayList<MedicationModel>();
	}
}
