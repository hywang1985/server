package clinics.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.BloodGroup;
import clinics.jpa.services.BloodGroupRepositoryService;
import clinics.model.meta.BloodGroupModel;
import clinics.transformer.BloodGroupTransformer;

@Service
public class BloodGroupService extends AbstractServiceImpl<Integer, BloodGroupModel, BloodGroup, BloodGroupRepositoryService, BloodGroupTransformer> {

	@Autowired
	private BloodGroupRepositoryService bloodGroupRepositoryService;

	@Autowired
	private BloodGroupTransformer bloodGroupTransformer;

	@Override
	protected BloodGroupRepositoryService repoService() {
		return bloodGroupRepositoryService;
	}

	@Override
	protected BloodGroupTransformer transformer() {
		return bloodGroupTransformer;
	}

	public BloodGroupModel getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public BloodGroupModel save(BloodGroupModel resource) {
		BloodGroupModel saved = super.save(resource);
		return saved;
	}
}
