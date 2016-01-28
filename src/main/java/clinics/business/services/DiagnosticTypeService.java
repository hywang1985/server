package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.DiagnosticType;
import clinics.jpa.services.DiagnosticTypeRepositoryService;
import clinics.model.DiagnosticTypeModel;
import clinics.transformer.DiagnosticTypeTransformer;

@Service
public class DiagnosticTypeService extends AbstractServiceImpl<Integer, DiagnosticTypeModel, DiagnosticType, DiagnosticTypeRepositoryService, DiagnosticTypeTransformer> {

	@Autowired
	private DiagnosticTypeRepositoryService configurationRepositoryService;

	@Autowired
	private DiagnosticTypeTransformer configurationTransformer;

	@Override
	protected DiagnosticTypeRepositoryService repoService() {
		return configurationRepositoryService;
	}

	@Override
	protected DiagnosticTypeTransformer transformer() {
		return configurationTransformer;
	}

	public List<DiagnosticTypeModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}
}
