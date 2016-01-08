package clinics.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Equipment;
import clinics.entity.Room;
import clinics.entity.RoomEquipment;
import clinics.jpa.services.EquipmentRepositoryService;
import clinics.jpa.services.RoomRepositoryService;
import clinics.model.IdValueModel;
import clinics.model.RoomModel;
import clinics.transformer.RoomTransformer;

@Service
public class RoomService extends AbstractServiceImpl<Integer, RoomModel, Room, RoomRepositoryService, RoomTransformer> {

	@Autowired
	private RoomRepositoryService roomRepositoryService;

	@Autowired
	private RoomTransformer roomTransformer;

	@Autowired
	private EquipmentRepositoryService equipmentRepositoryService;

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
		Room room = transformer().transformFrom(resource);
		List<IdValueModel> equipments = resource.getEquipments();
		if (null != equipments) {
			for (IdValueModel equipId : equipments) {
				Equipment e = equipmentRepositoryService.findOne(equipId.getId());
				if (e != null) {
					RoomEquipment equip = new RoomEquipment();
					if (null != e.getCommon() && e.getCommon()) {
						equip.setQuantity(equipId.getValue());
					} else {
						equip.setQuantity(1);
					}
					equip.setRoom(room);
					equip.setEquipment(e);
			        room.getRoomEquipments().add(equip);
			        equipmentRepositoryService.save(e);
			        repoService().save(room);
				}
			}
		}
		return resource;
	}

	@Override
	public List<RoomModel> getAll() {
		List<Room> entities = repoService().findAll();
		List<RoomModel> result = new ArrayList<RoomModel>();
		for (Room room : entities) {
			RoomModel m = toModel(room);
			result.add(m);
		}
		return result;
	}

	private RoomModel toModel(Room room) {
		RoomModel m = transformer().transformTo(room);
		for (RoomEquipment roomEquip : room.getRoomEquipments()) {
			m.getEquipments().add(new IdValueModel(roomEquip.getEquipment().getId(), roomEquip.getQuantity()));
		}
		return m;
	}
}
