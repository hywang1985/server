package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class StaffDepartmentId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Department department;

	private Staff staff;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffDepartmentId other = (StaffDepartmentId) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (staff == null) {
			if (other.staff != null)
				return false;
		} else if (!staff.equals(other.staff))
			return false;
		return true;
	}

	@ManyToOne
	public Department getDepartment() {
		return department;
	}

	@ManyToOne
	public Staff getStaff() {
		return staff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((staff == null) ? 0 : staff.hashCode());
		return result;
	}

	public void setDepartment(Department equipment) {
		this.department = equipment;
	}

	public void setStaff(Staff room) {
		this.staff = room;
	}
}
