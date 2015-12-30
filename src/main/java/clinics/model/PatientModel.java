package clinics.model;

public class PatientModel extends PersonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4391452643936417328L;

	private int bloodGroup;

	public int getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(int bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

}