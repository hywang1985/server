package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RoomEquipmentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Room room;

	private Equipment equipment;

	@ManyToOne
	public Room getRoom() {
		return room;
	}

	@ManyToOne
	public Equipment getEquipment() {
		return equipment;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
