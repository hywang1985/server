package clinics.model;

import java.util.ArrayList;
import java.util.List;

public class StaffModel extends PersonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7193154699034956133L;

	private List<String> qualifications = new ArrayList<String>();

	private List<String> specialities = new ArrayList<String>();

	private List<IdValueModel> departments = new ArrayList<IdValueModel>();

	public List<String> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<String> qualifications) {
		if (qualifications != null && qualifications.size() > 0) {
			this.qualifications.addAll(qualifications);
		}
	}

	public List<String> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<String> specialities) {
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
}