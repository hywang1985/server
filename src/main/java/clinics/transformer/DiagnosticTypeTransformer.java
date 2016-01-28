package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.DiagnosticType;
import clinics.model.DiagnosticTypeModel;

@Component
public class DiagnosticTypeTransformer extends AbstractDTOTransformer<DiagnosticTypeModel, DiagnosticType> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public DiagnosticType transformFrom(DiagnosticTypeModel source) {
		DiagnosticType dest = null;
		if (source != null) {
			try {
				dest = new DiagnosticType();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public DiagnosticTypeModel transformTo(DiagnosticType source) {
		DiagnosticTypeModel dest = null;
		if (source != null) {
			try {
				dest = new DiagnosticTypeModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}
