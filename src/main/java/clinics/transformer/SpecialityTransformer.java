package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Speciality;
import clinics.model.SpecialityModel;

@Component
public class SpecialityTransformer extends AbstractDTOTransformer<SpecialityModel, Speciality> {

	private static final String[] FROM_EXCLUDES = new String[] { "staffSpecialities" };
	private static final String[] TO_EXCLUDES = new String[] { "staffSpecialities" };

	@Override
	public Speciality transformFrom(SpecialityModel source) {
		Speciality dest = null;
		if (source != null) {
			try {
				dest = new Speciality();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public SpecialityModel transformTo(Speciality source) {
		SpecialityModel dest = null;
		if (source != null) {
			try {
				dest = new SpecialityModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}