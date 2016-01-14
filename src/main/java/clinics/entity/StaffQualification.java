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
@Table(name = "staff_qualification")
@AssociationOverrides({
		@AssociationOverride(name = "id.staff", joinColumns = @JoinColumn(name = "staff_id", insertable = false, updatable = false) ),
		@AssociationOverride(name = "id.qualification", joinColumns = @JoinColumn(name = "qualification_id", insertable = false, updatable = false) )
})
public class StaffQualification extends BaseEntity<StaffQualificationId> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4435473629958196511L;

	public StaffQualification() {
		this.id = new StaffQualificationId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffQualification other = (StaffQualification) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public Qualification getQualification() {
		return getId().getQualification();
	}

	@Override
	@EmbeddedId
	public StaffQualificationId getId() {
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

	public void setQualification(Qualification qualification) {
		this.getId().setQualification(qualification);
	}

	@Override
	public void setId(StaffQualificationId id) {
		this.id = id;
	}

	public void setStaff(Staff room) {
		this.getId().setStaff(room);
	}
}
