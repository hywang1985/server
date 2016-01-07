package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RoomEquipmentId extends CompositeID<Integer, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Integer room;

	private Integer equipment;

	public Integer getE1() {
		return room;
	}

	public Integer getE2() {
		return equipment;
	}

	public void setE1(Integer room) {
		this.room = room;
	}

	public void setE2(Integer equipment) {
		this.equipment = equipment;
	}
}
