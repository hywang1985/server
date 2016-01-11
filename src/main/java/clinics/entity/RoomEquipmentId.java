package clinics.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RoomEquipmentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Equipment equipment;

	private Room room;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomEquipmentId other = (RoomEquipmentId) obj;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Equipment getEquipment() {
		return equipment;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Room getRoom() {
		return room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
