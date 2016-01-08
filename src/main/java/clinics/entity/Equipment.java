package clinics.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "equipment", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides( value = 
{
    @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
    @AttributeOverride(name = "name", column = @Column(name = "name")),
    @AttributeOverride(name = "description", column = @Column(name = "description")),
    @AttributeOverride(name = "common", column = @Column(name = "common")),
    @AttributeOverride(name = "working", column = @Column(name = "working"))
})
public class Equipment extends BaseEntity<Integer> {
	private Boolean common;
	private String description;
	private String name;
	private Set<RoomEquipment> roomEquipments;
	private Boolean working;

	public Equipment() {
		roomEquipments = new HashSet<>();
	}

	public Equipment(String name) {
		this.name = name;
	}

	public Boolean getCommon() {
		return common;
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

	@OneToMany(mappedBy = "equipment")
	public Set<RoomEquipment> getRoomEquipments() {
		return roomEquipments;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setCommon(Boolean common) {
		this.common = common;
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

	public void setRoomEquipments(Set<RoomEquipment> roomEquipments) {
		this.roomEquipments = roomEquipments;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}
}
