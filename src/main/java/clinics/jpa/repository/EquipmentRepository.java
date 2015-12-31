package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Equipment;

@Repository
public interface EquipmentRepository extends BaseRepository<Equipment, Integer> {

	public Equipment findByName(String name);

	public List<Equipment> findAllByNameLike(String name);

	public List<Equipment> findByWorking(Boolean working);

	public List<Equipment> findByCommon(Boolean common);

}
