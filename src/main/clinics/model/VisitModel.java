package clinics.model;

import clinics.enums.VisitType;

public class VisitModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362208769594760467L;
	
	private Integer patientId;

	private Double weight;

	private Double height;

	private Integer respiRate;

	private Integer sBp;

	private Integer dBp;

	private Integer bodyTemp;

	private VisitType type;

	private Integer room;

	private String visitDate;

	private String dischargeDate;

	private Integer referredBy;

	private String reason;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getRespiRate() {
		return respiRate;
	}

	public void setRespiRate(Integer respiRate) {
		this.respiRate = respiRate;
	}

	public Integer getsBp() {
		return sBp;
	}

	public void setsBp(Integer sBp) {
		this.sBp = sBp;
	}

	public Integer getdBp() {
		return dBp;
	}

	public void setdBp(Integer dBp) {
		this.dBp = dBp;
	}

	public Integer getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Integer bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public VisitType getType() {
		return type;
	}

	public void setType(VisitType type) {
		this.type = type;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Integer getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(Integer referredBy) {
		this.referredBy = referredBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
