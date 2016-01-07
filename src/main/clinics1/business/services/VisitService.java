package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Visit;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.VisitModel;
import clinics.transformer.VisitTransformer;

@Service
public class VisitService extends AbstractServiceImpl<Integer, VisitModel, Visit, VisitRepositoryService, VisitTransformer> {

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Autowired
	private VisitTransformer visitTransformer;

	@Override
	protected VisitRepositoryService repoService() {
		return visitRepositoryService;
	}

	@Override
	public VisitTransformer transformer() {
		return visitTransformer;
	}

	public List<VisitModel> getAllVisitsOfPatient(Integer patientId) {
		return transformer().transformTo(repoService().findByPatientId(patientId));
	}

	public void removeByPatientId(Integer patientId) {
		List<VisitModel> visits = getAllVisitsOfPatient(patientId);
		for (VisitModel visitModel : visits) {
			repoService().delete(visitModel.getId());
		}
	}
}
