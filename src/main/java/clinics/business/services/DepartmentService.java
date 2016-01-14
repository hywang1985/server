package clinics.business.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Department;
import clinics.entity.StaffDepartment;
import clinics.jpa.services.DepartmentRepositoryService;
import clinics.model.DepartmentModel;
import clinics.transformer.DepartmentTransformer;

@Service
public class DepartmentService extends AbstractServiceImpl<Integer, DepartmentModel, Department, DepartmentRepositoryService, DepartmentTransformer> {

	@Autowired
	private DepartmentRepositoryService departmentRepositoryService;

	@Autowired
	private DepartmentTransformer departmentTransformer;

	@Override
	protected DepartmentRepositoryService repoService() {
		return departmentRepositoryService;
	}

	@Override
	protected DepartmentTransformer transformer() {
		return departmentTransformer;
	}

	public List<DepartmentModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public DepartmentModel save(DepartmentModel resource) {
		Department equipment = transformer().transformFrom(resource);
		if (null != equipment.getId()) {
			Department fromDb = repoService().findOne(equipment.getId());
			Set<StaffDepartment> existing = fromDb.getStaffDepartments();
			equipment.getStaffDepartments().addAll(existing);
		}
		repoService().save(equipment);
		return resource;
	}

	public List<DepartmentModel> getAll() {
		return super.getAll();
	}
}
