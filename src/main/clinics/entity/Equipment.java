package clinics.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "EQUIPMENTS", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides( value = 
{
    @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
    @AttributeOverride(name = "name", column = @Column(name = "name")),
    @AttributeOverride(name = "description", column = @Column(name = "description")),
    @AttributeOverride(name = "working", column = @Column(name = "working")),
    @AttributeOverride(name = "common", column = @Column(name = "common"))
})
public class Equipment extends BaseEntity<Integer> implements Serializable {

    private static final long serialVersionUID = 4289151143888117381L;

    private String name;

	private String description;

	private Boolean working;

	private Boolean common;
	
	private Set<Room> rooms;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getWorking() {
		return working;
	}

	public Boolean getCommon() {
		return common;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public void setCommon(Boolean common) {
		this.common = common;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "EQUIPMENTS")
	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

	@Override
    public int hashCode() {
        return (new HashCodeBuilder()).append(this.id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment rhs = (Equipment) obj;
        return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
    }
}
