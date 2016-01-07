package clinics.transformer;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Visit;
import clinics.model.VisitModel;

@Component
public class VisitTransformer extends AbstractDTOTransformer<VisitModel, Visit> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public Visit transformFrom(VisitModel source) {
		Visit dest = null;
		if (source != null) {
			try {
				dest = new Visit();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				if (dest.getId() == null) {
					dest.setCreatedDate(new Date());
				} else {
					dest.setModifiedDate(new Date());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public VisitModel transformTo(Visit source) {
		VisitModel dest = null;
		if (source != null) {
			try {
				dest = new VisitModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}