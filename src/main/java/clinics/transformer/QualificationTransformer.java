package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Qualification;
import clinics.model.QualificationModel;

@Component
public class QualificationTransformer extends AbstractDTOTransformer<QualificationModel, Qualification> {

	private static final String[] FROM_EXCLUDES = new String[] { "staffQualifications" };
	private static final String[] TO_EXCLUDES = new String[] { "staffQualifications" };

	@Override
	public Qualification transformFrom(QualificationModel source) {
		Qualification dest = null;
		if (source != null) {
			try {
				dest = new Qualification();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public QualificationModel transformTo(Qualification source) {
		QualificationModel dest = null;
		if (source != null) {
			try {
				dest = new QualificationModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}