package clinics.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides( value = 
{
    @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
    @AttributeOverride(name = "name", column = @Column(name = "name")),
    @AttributeOverride(name = "description", column = @Column(name = "description")),
    @AttributeOverride(name = "allotable", column = @Column(name = "allotable")),
    @AttributeOverride(name = "occupancy", column = @Column(name = "occupancy"))
})
public class Room extends BaseEntity<Integer> {
	private Boolean allotable;
	private String description;
	private String name;
	private Integer occupancy;
	private Set<RoomEquipment> roomEquipments;

	public Room() {
		roomEquipments = new HashSet<>();
	}

	public Boolean getAllotable() {
		return allotable;
	}

	public String getDescription() {
		return description;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	@OneToMany(mappedBy = "room", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public Set<RoomEquipment> getRoomEquipments() {
		return roomEquipments;
	}

	public void setAllotable(Boolean allotable) {
		this.allotable = allotable;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setId(Integer id) {
		super.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}

	public void setRoomEquipments(Set<RoomEquipment> roomEquipments) {
		this.roomEquipments = roomEquipments;
	}
	
	public void addEquipment(Equipment e, Integer quantity) {
		RoomEquipment roomEquip = new RoomEquipment();
		roomEquip.setRoom(this);
		roomEquip.setEquipment(e);
		roomEquip.setQuantity(quantity);
		roomEquipments.add(roomEquip);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
