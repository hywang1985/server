package clinics.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "appointment", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "name", column = @Column(name = "name")),
		@AttributeOverride(name = "reason", column = @Column(name = "reason")),
		@AttributeOverride(name = "dateTime", column = @Column(name = "dateTime"))
})
public class Appointment extends BaseEntity<Integer> {
	private String dateTime;
	private Department department;
	private Staff doctor;
	private String name;
	private Patient patient;
	private String reason;
	private Boolean done;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDateTime() {
		return dateTime;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	public Department getDepartment() {
		return department;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	public Staff getDoctor() {
		return doctor;
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

	@ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
	public Patient getPatient() {
		return patient;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDoctor(Staff doctor) {
		this.doctor = doctor;
	}

	@Override
	public void setId(Integer id) {
		super.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public Boolean getDone() {
		return done;
	}
}
