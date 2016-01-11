package clinics.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "room_equipment")
@AssociationOverrides({
	@AssociationOverride(name = "id.room", joinColumns = @JoinColumn(name = "room_id", insertable = false, updatable = false) ),
	@AssociationOverride(name = "id.equipment", joinColumns = @JoinColumn(name = "equipment_id", insertable = false, updatable = false) )
})
public class RoomEquipment extends BaseEntity<RoomEquipmentId> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435473629958196511L;

	private Integer quantity;
	
	public RoomEquipment() {
		this.id = new RoomEquipmentId();
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public Equipment getEquipment() {
		return getId().getEquipment();
	}

	@Override
	@EmbeddedId
	public RoomEquipmentId getId() {
		return id;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	@Transient
	public Room getRoom() {
		return getId().getRoom();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setEquipment(Equipment equipment) {
		this.getId().setEquipment(equipment);
	}

	@Override
	public void setId(RoomEquipmentId id) {
		this.id = id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setRoom(Room room) {
		this.getId().setRoom(room);
	}
}
