package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class StaffSpecialityId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Speciality speciality;

	private Staff staff;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffSpecialityId other = (StaffSpecialityId) obj;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		if (staff == null) {
			if (other.staff != null)
				return false;
		} else if (!staff.equals(other.staff))
			return false;
		return true;
	}

	@ManyToOne
	public Speciality getSpeciality() {
		return speciality;
	}

	@ManyToOne
	public Staff getStaff() {
		return staff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + ((staff == null) ? 0 : staff.hashCode());
		return result;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public void setStaff(Staff room) {
		this.staff = room;
	}
}
