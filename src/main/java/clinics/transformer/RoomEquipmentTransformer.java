package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.RoomEquipment;
import clinics.model.meta.RoomEquipmentModel;

@Component
public class RoomEquipmentTransformer extends AbstractDTOTransformer<RoomEquipmentModel, RoomEquipment> {

	private static final String[] FROM_EXCLUDES = new String[] {};
	private static final String[] TO_EXCLUDES = new String[] {};

	@Override
	public RoomEquipment transformFrom(RoomEquipmentModel source) {
		RoomEquipment dest = null;
		if (source != null) {
			try {
				dest = new RoomEquipment();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public RoomEquipmentModel transformTo(RoomEquipment source) {
		RoomEquipmentModel dest = null;
		if (source != null) {
			try {
				dest = new RoomEquipmentModel();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}