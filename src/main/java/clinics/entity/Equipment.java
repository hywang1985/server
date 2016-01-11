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

	@OneToMany(mappedBy = "equipment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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
		Equipment other = (Equipment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
