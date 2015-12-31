package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.RoomEquipment;
import clinics.entity.RoomEquipmentId;

@Repository
public interface RoomEquipmentRepository extends BaseRepository<RoomEquipment, RoomEquipmentId> {

	public List<RoomEquipment> findAllByRoomId(Integer id);

}
