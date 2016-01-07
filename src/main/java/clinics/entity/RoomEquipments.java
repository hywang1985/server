package clinics.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ROOMS_EQUIPMENTS")
@AssociationOverrides({
	@AssociationOverride(name = "id.room", joinColumns = @JoinColumn(name = "ROOM_ID")),
	@AssociationOverride(name = "id.equipment", joinColumns = @JoinColumn(name = "EQUIPMENT_ID"))
})
@AttributeOverrides( value = 
{
    @AttributeOverride(name = "quantity", column = @Column(name = "qty"))
})
public class RoomEquipments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4166810649852706093L;

	private RoomEquipmentId id = new RoomEquipmentId();
	
	private Integer quantity;

	@EmbeddedId
	public RoomEquipmentId getId() {
		return id;
	}

	public void setId(RoomEquipmentId pk) {
		this.id = pk;
	}

	@Transient
	public Room getRoom() {
		return getId().getRoom();
	}

	public void setRoom(Room room) {
		getId().setRoom(room);
	}

	@Transient
	public Equipment getEquipment() {
		return getId().getEquipment();
	}

	public void setEquipment(Equipment equipment) {
		getId().setEquipment(equipment);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}