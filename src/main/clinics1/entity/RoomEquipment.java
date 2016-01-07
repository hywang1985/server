package clinics.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROOM_EQUIPMENT")
@AttributeOverrides( value = {
    @AttributeOverride(name = "quantity", column = @Column(name = "quantity"))//,
//    @AttributeOverride(name = "room", column = @Column(name = "ROOM_ID", insertable = false, updatable = false)),
//    @AttributeOverride(name = "equipment", column = @Column(name = "EQUIPMENT_ID", insertable = false, updatable = false))
})
@AssociationOverrides({
	@AssociationOverride(name = "id.room",  joinColumns = @JoinColumn(name = "ROOM_ID")),
	@AssociationOverride(name = "id.equipment",  joinColumns = @JoinColumn(name = "EQUIPMENT_ID"))
})
public class RoomEquipment extends BaseEntity<RoomEquipmentId> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4166810649852706093L;

	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	@Id
	public RoomEquipmentId getId() {
		return id;
	}

	@Override
	public void setId(RoomEquipmentId id) {
		this.id = id;
	}

	public Integer getRoom() {
		return getId().getE1();
	}

	public void setRoom(Integer room) {
		getId().setE1(room);
	}

	public Integer getEquipment() {
		return getId().getE2();
	}

	public void setEquipment(Integer equipment) {
		getId().setE2(equipment);
	}
}
