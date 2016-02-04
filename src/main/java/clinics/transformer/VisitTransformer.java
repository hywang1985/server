package clinics.transformer;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Visit;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.VisitModel;

@Component
public class VisitTransformer extends AbstractDTOTransformer<VisitModel, Visit> {

	private static final String[] FROM_EXCLUDES = new String[] { "room" };
	private static final String[] TO_EXCLUDES = new String[] { "room" };

	@Autowired
	private PatientRepositoryService patientRepositoryService;

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Override
	public Visit transformFrom(VisitModel source) {
		Visit dest = null;
		if (source != null) {
			try {
				dest = new Visit();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				if (source.getId() == null) {
					dest.setCreatedDate(new Date());
					if (null != source.getPatientId()) {
						dest.setPatient(patientRepositoryService.findOne(source.getPatientId()));
					}
				} else {
					dest.setModifiedDate(new Date());
					Visit fromDb = visitRepositoryService.findOne(source.getId());
					dest.setPatient(fromDb.getPatient());
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
				if (null != source.getPatient()) {
					dest.setPatientId(source.getPatient().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}