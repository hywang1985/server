package clinics.business.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Qualification;
import clinics.entity.StaffQualification;
import clinics.jpa.services.QualificationRepositoryService;
import clinics.model.QualificationModel;
import clinics.transformer.QualificationTransformer;

@Service
public class QualificationService extends AbstractServiceImpl<Integer, QualificationModel, Qualification, QualificationRepositoryService, QualificationTransformer> {

	@Autowired
	private QualificationRepositoryService departmentRepositoryService;

	@Autowired
	private QualificationTransformer departmentTransformer;

	@Override
	protected QualificationRepositoryService repoService() {
		return departmentRepositoryService;
	}

	@Override
	protected QualificationTransformer transformer() {
		return departmentTransformer;
	}

	public List<QualificationModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public QualificationModel save(QualificationModel resource) {
		Qualification equipment = transformer().transformFrom(resource);
		if (null != equipment.getId()) {
			Qualification fromDb = repoService().findOne(equipment.getId());
			Set<StaffQualification> existing = fromDb.getStaffQualifications();
			equipment.getStaffQualifications().addAll(existing);
		}
		repoService().save(equipment);
		return resource;
	}

	public List<QualificationModel> getAll() {
		return super.getAll();
	}
}
