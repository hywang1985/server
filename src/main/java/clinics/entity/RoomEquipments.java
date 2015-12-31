package clinics.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ROOMS_EQUIPMENTS")
@AssociationOverrides({
		@AssociationOverride(name = "pk.room", joinColumns = @JoinColumn(name = "ROOM_ID")),
		@AssociationOverride(name = "pk.equipment", joinColumns = @JoinColumn(name = "EQUIPMENT_ID"))
})
public class RoomEquipments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4166810649852706093L;

	private RoomEquipmentId pk = new RoomEquipmentId();

	@EmbeddedId
	public RoomEquipmentId getPk() {
		return pk;
	}

	public void setPk(RoomEquipmentId pk) {
		this.pk = pk;
	}

	@Transient
	public Room getRoom() {
		return getPk().getRoom();
	}

	public void setRoom(Room room) {
		getPk().setRoom(room);
	}

	@Transient
	public Equipment getEquipment() {
		return getPk().getEquipment();
	}

	public void setEquipment(Equipment equipment) {
		getPk().setEquipment(equipment);
	}

}
