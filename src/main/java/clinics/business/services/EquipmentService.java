package clinics.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Equipment;
import clinics.entity.RoomEquipment;
import clinics.jpa.services.EquipmentRepositoryService;
import clinics.model.EquipmentModel;
import clinics.transformer.EquipmentTransformer;

@Service
public class EquipmentService extends AbstractServiceImpl<Integer, EquipmentModel, Equipment, EquipmentRepositoryService, EquipmentTransformer> {

	@Autowired
	private EquipmentRepositoryService equipmentRepositoryService;

	@Autowired
	private EquipmentTransformer equipmentTransformer;

	@Override
	protected EquipmentRepositoryService repoService() {
		return equipmentRepositoryService;
	}

	@Override
	protected EquipmentTransformer transformer() {
		return equipmentTransformer;
	}

	public List<EquipmentModel> getByName(String name) {
		return transformer().transformTo(repoService().findByName(name));
	}

	@Override
	public EquipmentModel save(EquipmentModel resource) {
		Equipment equipment = transformer().transformFrom(resource);
		if (null != equipment.getId()) {
			Equipment fromDb = repoService().findOne(equipment.getId());
			Set<RoomEquipment> existing = fromDb.getRoomEquipments();
			equipment.getRoomEquipments().addAll(existing);
		}
		repoService().save(equipment);
		return resource;
	}

	public List<EquipmentModel> getAll(Boolean working) {
		List<EquipmentModel> equipments = new ArrayList<EquipmentModel>();
		if (null == working) {
			equipments.addAll(super.getAll());
		} else {
			equipments.addAll(transformer().transformTo(repoService().findByWorkingItems(working)));
		}
		return equipments;
	}

	public List<EquipmentModel> getAvailableEquipments() {
		List<EquipmentModel> all = getAll(true);
		Set<EquipmentModel> allotableEquipments = transformer().transformTo(repoService().findAllotableEquipments());
		for (EquipmentModel equipmentModel : all) {
			EquipmentModel from = getFromAll(allotableEquipments, equipmentModel.getId());
			if (null != from) {
				equipmentModel.setAllotable(true);
			} else {
				equipmentModel.setAllotable(false);
			}
		}
		return all;
	}

	private EquipmentModel getFromAll(Set<EquipmentModel> all, Integer integer) {
		for (EquipmentModel equipmentModel : all) {
			if (equipmentModel.getId() == integer) {
				return equipmentModel;
			}
		}
		return null;
	}
}
