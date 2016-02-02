package clinics.model;

import java.util.ArrayList;
import java.util.List;

public class StaffModel extends PersonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7193154699034956133L;

	private List<IdValueModel> qualifications = new ArrayList<IdValueModel>();

	private List<IdValueModel> specialities = new ArrayList<IdValueModel>();

	private List<IdValueModel> departments = new ArrayList<IdValueModel>();
	
	private Double consultationFee;

	private String doj;

	private Boolean sun;

	private Boolean mon;

	private Boolean tue;

	private Boolean wed;

	private Boolean thu;

	private Boolean fri;

	private Boolean sat;
	
	private Integer user;

	public List<IdValueModel> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<IdValueModel> qualifications) {
		if (qualifications != null && qualifications.size() > 0) {
			this.qualifications.addAll(qualifications);
		}
	}

	public List<IdValueModel> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<IdValueModel> specialities) {
		if (specialities != null && specialities.size() > 0) {
			this.specialities.addAll(specialities);
		}
	}

	public List<IdValueModel> getDepartments() {
		return departments;
	}

	public void setDepartments(List<IdValueModel> departments) {
		if (departments != null && departments.size() > 0) {
			this.departments.addAll(departments);
		}
	}

	public Double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(Double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public Boolean getSun() {
		return sun;
	}

	public Boolean getMon() {
		return mon;
	}

	public Boolean getTue() {
		return tue;
	}

	public Boolean getWed() {
		return wed;
	}

	public Boolean getThu() {
		return thu;
	}

	public Boolean getFri() {
		return fri;
	}

	public Boolean getSat() {
		return sat;
	}

	public void setSun(Boolean sun) {
		this.sun = sun;
	}

	public void setMon(Boolean mon) {
		this.mon = mon;
	}

	public void setTue(Boolean tue) {
		this.tue = tue;
	}

	public void setWed(Boolean wed) {
		this.wed = wed;
	}

	public void setThu(Boolean thu) {
		this.thu = thu;
	}

	public void setFri(Boolean fri) {
		this.fri = fri;
	}

	public void setSat(Boolean sat) {
		this.sat = sat;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}
}