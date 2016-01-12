package clinics.business.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
	
	@Autowired
	private VisitService visitService;

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
		Room fromDb = null;
		Set<RoomEquipment> existing = null;
		if (null != room.getId()) {
			fromDb = repoService().findOne(room.getId());
			existing = fromDb.getRoomEquipments();
		}
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
		repoService().save(room);
		return resource;
	}
	
	public List<RoomModel> getAll() {
		return entitiesToModels(repoService().findAll());
	}

	public List<RoomModel> getAll(Boolean allotable) {
		List<Room> entities = null;
		if (null == allotable) {
			entities = repoService().findAll();
		} else {
			entities = repoService().findByAllotable(allotable);
		}

		return entitiesToModels(entities);
	}

	private List<RoomModel> entitiesToModels(List<Room> entities) {
		List<RoomModel> rooms = new ArrayList<RoomModel>();
		for (Room room : entities) {
			RoomModel m = toModel(room);
			rooms.add(m);
		}
		return rooms;
	}

	private RoomModel toModel(Room room) {
		RoomModel roomModel = transformer().transformTo(room);
		for (RoomEquipment roomEquip : room.getRoomEquipments()) {
			roomModel.getEquipments().add(new IdValueModel(roomEquip.getEquipment().getId(), roomEquip.getQuantity()));
		}
		roomModel.setAvailable(visitService.countOccupiedBedsOfRoom(roomModel.getId()));
		return roomModel;
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

	public void removeRoomEquipmentById(int roomId, int equipmentId) {
		Room fromDb = repoService().findOne(roomId);
		if (null != fromDb.getId()) {
			Iterator<RoomEquipment> roomEquipmentsIterator = fromDb.getRoomEquipments().iterator();
			while (roomEquipmentsIterator.hasNext()) {
				RoomEquipment roomEquipment = roomEquipmentsIterator.next();
				if (equipmentId == roomEquipment.getEquipment().getId()) {
					roomEquipmentsIterator.remove();
				}
			}
			repoService().save(fromDb);
		}
	}

	public List<RoomModel> getAvailableRooms() {
		List<RoomModel> allotableRooms = getAll(true);
		ListIterator<RoomModel> iterator = allotableRooms.listIterator();
		while (iterator.hasNext()) {
			RoomModel roomModel = (RoomModel) iterator.next();
			if (roomModel.getOccupancy() - roomModel.getAvailable() <= 0) {
				iterator.remove();
			}
		}
		return allotableRooms;
	}
}
