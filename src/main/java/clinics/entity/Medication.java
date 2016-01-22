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

import clinics.enums.MedicationRule;
import clinics.enums.QuantityType;

@Entity
@Table(name = "medications", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "name", column = @Column(name = "name")),
		@AttributeOverride(name = "quantity", column = @Column(name = "quantity")),
		@AttributeOverride(name = "quantityType", column = @Column(name = "quantity_type")),
		@AttributeOverride(name = "morning", column = @Column(name = "morning")),
		@AttributeOverride(name = "afternoon", column = @Column(name = "afternoon")),
		@AttributeOverride(name = "night", column = @Column(name = "night")),
		@AttributeOverride(name = "morningRule", column = @Column(name = "morning_rule")),
		@AttributeOverride(name = "afternoonRule", column = @Column(name = "afternoon_rule")),
		@AttributeOverride(name = "nightRule", column = @Column(name = "night_rule"))
})
public class Medication extends BaseEntity<Integer> {
	private String name;
	private Integer quantity;
	private QuantityType quantityType;
	private Staff doctor;
	private Patient patient;
	private Visit visit;
	private Boolean morning;
	private Boolean afternoon;
	private Boolean night;
	private MedicationRule morningRule;
	private MedicationRule afternoonRule;
	private MedicationRule nightRule;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
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

	public void setName(String name) {
		this.name = name;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public QuantityType getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(QuantityType quantityType) {
		this.quantityType = quantityType;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Boolean getMorning() {
		return morning;
	}

	public void setMorning(Boolean morning) {
		this.morning = morning;
	}

	public Boolean getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Boolean afternoon) {
		this.afternoon = afternoon;
	}

	public Boolean getNight() {
		return night;
	}

	public void setNight(Boolean night) {
		this.night = night;
	}

	public MedicationRule getMorningRule() {
		return morningRule;
	}

	public void setMorningRule(MedicationRule morningRule) {
		this.morningRule = morningRule;
	}

	public MedicationRule getAfternoonRule() {
		return afternoonRule;
	}

	public void setAfternoonRule(MedicationRule afternoonRule) {
		this.afternoonRule = afternoonRule;
	}

	public MedicationRule getNightRule() {
		return nightRule;
	}

	public void setNightRule(MedicationRule nightRule) {
		this.nightRule = nightRule;
	}

}
