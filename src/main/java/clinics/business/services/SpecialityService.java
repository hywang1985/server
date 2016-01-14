package clinics.business.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Speciality;
import clinics.entity.StaffSpeciality;
import clinics.jpa.services.SpecialityRepositoryService;
import clinics.model.SpecialityModel;
import clinics.transformer.SpecialityTransformer;

@Service
public class SpecialityService extends AbstractServiceImpl<Integer, SpecialityModel, Speciality, SpecialityRepositoryService, SpecialityTransformer> {

	@Autowired
	private SpecialityRepositoryService departmentRepositoryService;

	@Autowired
	private SpecialityTransformer departmentTransformer;

	@Override
	protected SpecialityRepositoryService repoService() {
		return departmentRepositoryService;
	}

	@Override
	protected SpecialityTransformer transformer() {
		return departmentTransformer;
	}

	public List<SpecialityModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public SpecialityModel save(SpecialityModel resource) {
		Speciality equipment = transformer().transformFrom(resource);
		if (null != equipment.getId()) {
			Speciality fromDb = repoService().findOne(equipment.getId());
			Set<StaffSpeciality> existing = fromDb.getStaffSpecialities();
			equipment.getStaffSpecialities().addAll(existing);
		}
		repoService().save(equipment);
		return resource;
	}

	public List<SpecialityModel> getAll() {
		return super.getAll();
	}
}
