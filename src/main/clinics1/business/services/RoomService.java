package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Room;
import clinics.jpa.services.RoomEquipmentRepositoryService;
import clinics.jpa.services.RoomRepositoryService;
import clinics.model.RoomModel;
import clinics.model.meta.RoomEquipmentModel;
import clinics.transformer.RoomEquipmentTransformer;
import clinics.transformer.RoomTransformer;

@Service
public class RoomService extends AbstractServiceImpl<Integer, RoomModel, Room, RoomRepositoryService, RoomTransformer> {

	@Autowired
	private RoomRepositoryService roomRepositoryService;

	@Autowired
	private RoomEquipmentRepositoryService roomEquipmentRepositoryService;

	@Autowired
	private RoomTransformer roomTransformer;

	@Autowired
	private RoomEquipmentTransformer roomEquipmentTransformer;

	@Override
	protected RoomRepositoryService repoService() {
		return roomRepositoryService;
	}

	@Override
	protected RoomTransformer transformer() {
		return roomTransformer;
	}

	public List<RoomModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public RoomModel save(RoomModel resource) {
		RoomModel response = super.save(resource);
		for (RoomEquipmentModel roomEquipment : resource.getEquipments()) {
			roomEquipmentRepositoryService.save(roomEquipmentTransformer.transformFrom(roomEquipment));
		}
		return response;
	}

	@Override
	public List<RoomModel> getAll() {
		List<RoomModel> response = super.getAll();
		for (RoomModel roomModel : response) {
			List<RoomEquipmentModel> equips = roomEquipmentTransformer
					.transformTo(roomEquipmentRepositoryService.findAllByRoomId(roomModel.getId()));
			if (equips != null && equips.size() > 0) {
				roomModel.getEquipments().addAll(equips);
			}
		}
		return response;
	}
}
