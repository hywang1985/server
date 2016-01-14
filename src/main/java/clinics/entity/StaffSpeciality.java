package clinics.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "staff_speciality")
@AssociationOverrides({
		@AssociationOverride(name = "id.staff", joinColumns = @JoinColumn(name = "staff_id", insertable = false, updatable = false) ),
		@AssociationOverride(name = "id.speciality", joinColumns = @JoinColumn(name = "speciality_id", insertable = false, updatable = false) )
})
public class StaffSpeciality extends BaseEntity<StaffSpecialityId> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435473629958196511L;

	public StaffSpeciality() {
		this.id = new StaffSpecialityId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffSpeciality other = (StaffSpeciality) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public Speciality getSpeciality() {
		return getId().getSpeciality();
	}

	@Override
	@EmbeddedId
	public StaffSpecialityId getId() {
		return id;
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

	public void setSpeciality(Speciality speciality) {
		this.getId().setSpeciality(speciality);
	}

	@Override
	public void setId(StaffSpecialityId id) {
		this.id = id;
	}

	public void setStaff(Staff room) {
		this.getId().setStaff(room);
	}
}
