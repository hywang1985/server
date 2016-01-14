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

	private String doj;

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

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}
}