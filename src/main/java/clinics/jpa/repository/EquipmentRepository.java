package clinics.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import clinics.entity.Equipment;

@Repository
public interface EquipmentRepository extends BaseRepository<Equipment, Integer> {

	public Equipment findByName(String name);

	public List<Equipment> findAllByNameLike(String name);

	public List<Equipment> findByWorking(Boolean working);

	public List<Equipment> findByCommon(Boolean common);

	@Query("SELECT e from RoomEquipment re RIGHT JOIN re.id.equipment e WHERE e.working = true AND (re.id.equipment is null or e.common = true)")
	public List<Equipment> findAllotableEquipments();

}