package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Category;
import clinics.model.DepartmentModel;

@Component
public class CategoryTransformer extends AbstractDTOTransformer<DepartmentModel, Category> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public Category transformFrom(DepartmentModel source) {
		Category dest = null;
		if (source != null) {
			try {
				dest = new Category();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public DepartmentModel transformTo(Category source) {
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
