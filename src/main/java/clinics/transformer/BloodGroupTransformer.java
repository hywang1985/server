package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.BloodGroup;
import clinics.model.meta.BloodGroupModel;

@Component
public class BloodGroupTransformer extends AbstractDTOTransformer<BloodGroupModel, BloodGroup> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public BloodGroup transformFrom(BloodGroupModel source) {
		BloodGroup dest = null;
		if (source != null) {
			try {
				dest = new BloodGroup();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public BloodGroupModel transformTo(BloodGroup source) {
		BloodGroupModel dest = null;
		if (source != null) {
			try {
				dest = new BloodGroupModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}
