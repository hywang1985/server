package clinics.transformer;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clinics.entity.Room;
import clinics.entity.Visit;
import clinics.jpa.services.PatientRepositoryService;
import clinics.jpa.services.RoomRepositoryService;
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
	
	@Autowired
	private RoomRepositoryService roomRepositoryService;

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
					if(null != fromDb) {
						if(null != fromDb.getPatient()) {
							dest.setPatient(fromDb.getPatient());
						}
						if(null != fromDb.getBills()) {
							dest.setBills(fromDb.getBills());
						}
						if(null != fromDb.getDiagnostics()) {
							dest.setDiagnostics(fromDb.getDiagnostics());
						}
						if(null != source.getRoom()) {
							Room room = roomRepositoryService.findOne(source.getRoom());
							dest.setRoom(room);
						}
						if(null != fromDb.getMedications()) {
							dest.setMedications(fromDb.getMedications());
						}
					}
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
				if (null != source.getRoom()) {
					dest.setRoom(source.getRoom().getId());
				}
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}