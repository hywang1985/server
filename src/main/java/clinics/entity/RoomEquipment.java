package clinics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room_equipment")
public class RoomEquipment implements Serializable {
	private Equipment equipment;
	private Integer quantity;
	private Room room;

	@Id
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	public Equipment getEquipment() {
		return equipment;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return room;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
