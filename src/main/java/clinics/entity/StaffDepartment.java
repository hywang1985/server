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
@Table(name = "staff_department")
@AssociationOverrides({
	@AssociationOverride(name = "id.staff", joinColumns = @JoinColumn(name = "staff_id", insertable = false, updatable = false) ),
	@AssociationOverride(name = "id.department", joinColumns = @JoinColumn(name = "department_id", insertable = false, updatable = false) )
})
public class StaffDepartment extends BaseEntity<StaffDepartmentId> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435473629958196511L;

	private Boolean chief;
	
	public StaffDepartment() {
		this.id = new StaffDepartmentId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffDepartment other = (StaffDepartment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public Department getDepartment() {
		return getId().getDepartment();
	}

	@Override
	@EmbeddedId
	public StaffDepartmentId getId() {
		return id;
	}

	@Column(name = "chief")
	public Boolean getChief() {
		return chief;
	}

	@Transient
	public Staff getStaff() {
		return getId().getStaff();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setDepartment(Department equipment) {
		this.getId().setDepartment(equipment);
	}

	@Override
	public void setId(StaffDepartmentId id) {
		this.id = id;
	}

	public void setChief(Boolean quantity) {
		this.chief = quantity;
	}

	public void setStaff(Staff room) {
		this.getId().setStaff(room);
	}
}
