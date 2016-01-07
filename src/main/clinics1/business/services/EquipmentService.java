package clinics.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinics.entity.Equipment;
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
		EquipmentModel saved = super.save(resource);
		return saved;
	}
}
