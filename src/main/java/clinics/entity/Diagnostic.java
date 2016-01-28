package clinics.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
@Table(name = "diagnostics", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }) )
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false) ),
		@AttributeOverride(name = "test", column = @Column(name = "test") ),
		@AttributeOverride(name = "date", column = @Column(name = "date") ),
		@AttributeOverride(name = "result", column = @Column(name = "result") ),
		@AttributeOverride(name = "interpretation", column = @Column(name = "interpretation") )
})
public class Diagnostic extends BaseEntity<Integer> {
	private DiagnosticType type;
	private String test;
	private String date;
	private String result;
	private String interpretation;
	private Staff doctor;
	private Patient patient;
	private Visit visit;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnostic other = (Diagnostic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	public Staff getDoctor() {
		return doctor;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	public Patient getPatient() {
		return patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setDoctor(Staff doctor) {
		this.doctor = doctor;
	}

	@Override
	public void setId(Integer id) {
		super.id = id;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	public DiagnosticType getType() {
		return type;
	}

	public void setType(DiagnosticType type) {
		this.type = type;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}

}
