package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Bill;
import clinics.entity.Patient;
import clinics.entity.Visit;
import clinics.jpa.services.BillRepositoryService;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.BillModel;
import clinics.transformer.BillTransformer;

@Service
public class BillService extends AbstractServiceImpl<Integer, BillModel, Bill, BillRepositoryService, BillTransformer> {

	@Autowired
	private BillRepositoryService billRepositoryService;

	@Autowired
	private BillTransformer billTransformer;

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Override
	protected BillRepositoryService repoService() {
		return billRepositoryService;
	}

	@Override
	protected BillTransformer transformer() {
		return billTransformer;
	}

	@Override
	public BillModel save(BillModel resource) {
		Patient patient = patientRepositoryService.findOne(resource.getPatient());
		Visit visit = visitRepositoryService.findOne(resource.getVisit());
		if (null != patient && null != visit) {
			resource = super.save(resource);
		}
		return resource;
	}

	public List<BillModel> getAll() {
		return super.getAll();
	}

	public List<BillModel> getPatientVisitBills(Integer patientId, Integer visitId) {
		Patient patient = patientRepositoryService.findOne(patientId);
		Visit visit = visitRepositoryService.findOne(visitId);
		if (null != patient && null != visit) {
			return transformer().transformTo(repoService().getPatientVisitBills(patient, visit));
		}
		return new ArrayList<BillModel>();
	}
}
