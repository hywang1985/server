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
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }) )
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false) ),
		@AttributeOverride(name = "name", column = @Column(name = "name") ),
		@AttributeOverride(name = "description", column = @Column(name = "description") )
})
public class Department extends BaseEntity<Integer> {
	private String description;
	private String name;
	private Boolean appointmentable;
	private Set<StaffDepartment> staffDepartments;

	public Department() {
		staffDepartments = new HashSet<StaffDepartment>();
	}

	public Department(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	@OneToMany(mappedBy = "id.department", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<StaffDepartment> getStaffDepartments() {
		return staffDepartments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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

	public void setStaffDepartments(Set<StaffDepartment> staffDepartments) {
		this.staffDepartments = staffDepartments;
	}

	public Boolean getAppointmentable() {
		return appointmentable;
	}

	public void setAppointmentable(Boolean appointmentable) {
		this.appointmentable = appointmentable;
	}

}
