package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Room;
import clinics.entity.Visit;
import clinics.jpa.services.RoomRepositoryService;
import clinics.jpa.services.VisitRepositoryService;
import clinics.model.VisitModel;
import clinics.transformer.VisitTransformer;

@Service
public class VisitService extends AbstractServiceImpl<Integer, VisitModel, Visit, VisitRepositoryService, VisitTransformer> {

	@Autowired
	private VisitRepositoryService visitRepositoryService;

	@Autowired
	private VisitTransformer visitTransformer;

	@Override
	protected VisitRepositoryService repoService() {
		return visitRepositoryService;
	}

	@Autowired
	private RoomRepositoryService roomRepositoryService;

	@Override
	public VisitTransformer transformer() {
		return visitTransformer;
	}

	@Override
	public VisitModel save(VisitModel resource) {
		Visit toSave = transformer().transformFrom(resource);
		Visit fromDb = null;
		Room existing = null;
		if (null != toSave.getId()) {
			fromDb = repoService().findOne(toSave.getId());
			existing = fromDb.getRoom();
		}

		if (null != resource.getRoom()) {
			Room roomEntity = roomRepositoryService.findOne(resource.getRoom());
			if (null != roomEntity) {
				if (null != existing) {
					toSave.setRoom(null);
				}
				toSave.setRoom(roomEntity);
			}
		}
		resource = transformer().transformTo(repoService().save(toSave));
		return resource;
	}

	public List<VisitModel> getAllVisitsOfPatient(Integer patientId) {
		List<Visit> patientEntities = repoService().findByPatientId(patientId);
		List<VisitModel> visits = transformer().transformTo(patientEntities);
		for (Visit visitEntity : patientEntities) {
			Integer visitId = visitEntity.getId();
			VisitModel eachVisit = getVisitById(visits, visitId);
			Room room = visitEntity.getRoom();
			if (null != room) {
				eachVisit.setRoom(room.getId());
			}
		}
		return visits;
	}

	private VisitModel getVisitById(List<VisitModel> visits, Integer visitId) {
		VisitModel right = null;
		for (VisitModel visitModel : visits) {
			if (visitModel.getId() == visitId) {
				right = visitModel;
				break;
			}
		}
		return right;
	}

	public void removeByPatientId(Integer patientId) {
		List<VisitModel> visits = getAllVisitsOfPatient(patientId);
		for (VisitModel visitModel : visits) {
			repoService().delete(visitModel.getId());
		}
	}

	public Integer countOccupiedBedsOfRoom(Integer roomId) {
		Integer occupiedBeds = visitRepositoryService.countByRoomId(roomId);
		return occupiedBeds;
	}
}
