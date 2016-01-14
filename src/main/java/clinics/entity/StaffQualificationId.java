package clinics.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class StaffQualificationId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7630068430046310767L;

	private Qualification qualification;

	private Staff staff;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffQualificationId other = (StaffQualificationId) obj;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (staff == null) {
			if (other.staff != null)
				return false;
		} else if (!staff.equals(other.staff))
			return false;
		return true;
	}

	@ManyToOne
	public Qualification getQualification() {
		return qualification;
	}

	@ManyToOne
	public Staff getStaff() {
		return staff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result + ((staff == null) ? 0 : staff.hashCode());
		return result;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public void setStaff(Staff room) {
		this.staff = room;
	}
}
