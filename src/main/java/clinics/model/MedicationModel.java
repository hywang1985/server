package clinics.model;

import clinics.enums.MedicationRule;
import clinics.enums.QuantityType;

public class MedicationModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5257830650224317268L;

	private Integer visit;
	private Integer doctor;
	private Integer patient;
	private String name;
	private Integer quantity;
	private QuantityType quantityType;
	private Boolean morning;
	private Boolean afternoon;
	private Boolean night;
	private MedicationRule morningRule;
	private MedicationRule afternoonRule;
	private MedicationRule nightRule;

	public Integer getDoctor() {
		return doctor;
	}

	public String getName() {
		return name;
	}

	public Integer getPatient() {
		return patient;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPatient(Integer patient) {
		this.patient = patient;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
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
