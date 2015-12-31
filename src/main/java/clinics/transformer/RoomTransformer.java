package clinics.transformer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import clinics.entity.Room;
import clinics.model.RoomModel;

@Component
public class RoomTransformer extends AbstractDTOTransformer<RoomModel, Room> {

	private static final String[] FROM_EXCLUDES = new String[] { "equipments" };
	private static final String[] TO_EXCLUDES = new String[] { "equipments" };

	@Override
	public Room transformFrom(RoomModel source) {
		Room dest = null;
		if (source != null) {
			try {
				dest = new Room();
				BeanUtils.copyProperties(source, dest, FROM_EXCLUDES);
				dest.setName(dest.getName().toUpperCase());
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}

	@Override
	public RoomModel transformTo(Room source) {
		RoomModel dest = null;
		if (source != null) {
			try {
				dest = new RoomModel();
				BeanUtils.copyProperties(source, dest, TO_EXCLUDES);
			} catch (Exception e) {
				dest = null;
			}
		}
		return dest;
	}
}