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

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
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
}
