package clinics.business.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		Room fromDb = repoService().findOne(room.getId());
		Set<RoomEquipment> existing = fromDb.getRoomEquipments();
		List<IdValueModel> equipments = resource.getEquipments();

		if (null != equipments && equipments.size() > 0) {
			for (IdValueModel equipId : equipments) {
				Equipment e = equipmentRepositoryService.findOne(equipId.getId());
				if (e != null) {
					Integer quantity = evaluateQuantity(equipId, e);
					if (room.getId() == null) {
						room.addEquipment(e, quantity);
					} else {
						RoomEquipment curr = getRoomEquipment(existing, e.getId());
						if (curr != null) {
							curr.setQuantity(quantity);
							room.updateEquipment(curr);
						} else {
							room.addEquipment(e, quantity);
						}
					}
				}
			}
		}
//		for (Iterator<RoomEquipment> roomEquipmentsIterator = fromDb.getRoomEquipments().iterator(); roomEquipmentsIterator.hasNext();) {
//			RoomEquipment roomEquipment = roomEquipmentsIterator.next();
//			if (isRemoved(equipments, roomEquipment)) {
//				roomEquipmentsIterator.remove();
//			}
//		}
//		repoService().save(fromDb);
		repoService().save(room);
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
	
	private Integer evaluateQuantity(IdValueModel equipId, Equipment e) {
		Integer quantity = 1;
		if (null != e.getCommon() && e.getCommon()) {
			quantity = equipId.getValue();
		}
		return quantity;
	}
	
	private RoomEquipment getRoomEquipment(Set<RoomEquipment> existing, Integer equipmentId) {
		for (RoomEquipment roomEquipment : existing) {
			if (roomEquipment.getId().getEquipment().getId() == equipmentId) {
				return roomEquipment;
			}
		}
		return null;
	}
	
	private boolean isRemoved(List<IdValueModel> equipments, RoomEquipment roomEquipment) {
		boolean flag = true;
		for (IdValueModel idValueModel : equipments) {
			if (idValueModel.getId() == roomEquipment.getEquipment().getId()) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
