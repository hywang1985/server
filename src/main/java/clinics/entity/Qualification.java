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
@Table(name = "qualification", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }) )
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false) ),
		@AttributeOverride(name = "name", column = @Column(name = "name") ),
		@AttributeOverride(name = "description", column = @Column(name = "description") )
})
public class Qualification extends BaseEntity<Integer> {
	private String description;
	private String name;
	private Set<StaffQualification> staffQualifications;

	public Qualification() {
		staffQualifications = new HashSet<StaffQualification>();
	}

	public Qualification(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Qualification other = (Qualification) obj;
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

	@OneToMany(mappedBy = "id.qualification", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<StaffQualification> getStaffQualifications() {
		return staffQualifications;
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

	public void setStaffQualifications(Set<StaffQualification> staffQualifications) {
		this.staffQualifications = staffQualifications;
	}
}
