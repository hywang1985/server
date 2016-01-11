package clinics.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room_equipment")
public class RoomEquipment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435473629958196511L;

	private Equipment equipment;
	private Integer quantity;
	private Room room;

	@Id
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "equipment_id", referencedColumnName = "id")
	public Equipment getEquipment() {
		return equipment;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	@Id
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "room_id", referencedColumnName = "id")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomEquipment other = (RoomEquipment) obj;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
	
	
}
