package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Department;
import clinics.model.DepartmentModel;

@Component
public class DepartmentTransformer extends AbstractDTOTransformer<DepartmentModel, Department> {

	private static final String[] FROM_EXCLUDES = new String[] { "staffDepartments" };
	private static final String[] TO_EXCLUDES = new String[] { "staffDepartments" };

	@Override
	public Department transformFrom(DepartmentModel source) {
		Department dest = null;
		if (source != null) {
			try {
				dest = new Department();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public DepartmentModel transformTo(Department source) {
		DepartmentModel dest = null;
		if (source != null) {
			try {
				dest = new DepartmentModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}