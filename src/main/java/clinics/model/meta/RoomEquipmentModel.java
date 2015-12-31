package clinics.model.meta;

import clinics.model.Model;

public class RoomEquipmentModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3535044193033485648L;

	private Integer quantity;

	private Integer equipment;
	
	private Integer room;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getEquipment() {
		return equipment;
	}

	public void setEquipment(Integer equipmentId) {
		this.equipment = equipmentId;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

}
