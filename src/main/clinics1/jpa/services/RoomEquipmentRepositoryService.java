package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.RoomEquipment;
import clinics.entity.RoomEquipmentId;
import clinics.jpa.repository.RoomEquipmentRepository;

@Service
public class RoomEquipmentRepositoryService extends AbstractRepositoryService<RoomEquipmentRepository, RoomEquipment, RoomEquipmentId> {

	@Autowired
	private RoomEquipmentRepository repository;

	@Override
	public RoomEquipmentRepository repository() {
		return repository;
	}

	public List<RoomEquipment> findAllByRoomId(Integer id) {
		return repository().findAllByRoomId(id);
	}
}
