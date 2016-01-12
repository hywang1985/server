package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Equipment;
import clinics.model.EquipmentModel;

@Component
public class EquipmentTransformer extends AbstractDTOTransformer<EquipmentModel, Equipment> {

	private static final String[] FROM_EXCLUDES = new String[] { "roomEquipments" };
	private static final String[] TO_EXCLUDES = new String[] { "roomEquipments" };

	@Override
	public Equipment transformFrom(EquipmentModel source) {
		Equipment dest = null;
		if (source != null) {
			try {
				dest = new Equipment();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public EquipmentModel transformTo(Equipment source) {
		EquipmentModel dest = null;
		if (source != null) {
			try {
				dest = new EquipmentModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}