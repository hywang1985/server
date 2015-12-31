package clinics.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Equipment;
import clinics.jpa.repository.EquipmentRepository;
import clinics.utils.Constants;

@Service
public class EquipmentRepositoryService extends AbstractRepositoryService<EquipmentRepository, Equipment, Integer> {

	@Autowired
	private EquipmentRepository repository;

	@Override
	public EquipmentRepository repository() {
		return repository;
	}

	public List<Equipment> findByName(String name) {
		return repository().findAllByNameLike(Constants.PERCENT + name.toUpperCase() + Constants.PERCENT);
	}

	public List<Equipment> findByWorkingItems(Boolean working) {
		return repository().findByWorking(working);
	}

	public List<Equipment> findByCommonItems(Boolean common) {
		return repository().findByWorking(common);
	}
}
