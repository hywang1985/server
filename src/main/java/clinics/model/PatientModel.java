package clinics.model;

public class PatientModel extends PersonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4391452643936417328L;

	private Double weight;

	private Double height;

	private String bloodGroup;

	public Double getWeight() {
		return weight;
	}

	public Double getHeight() {
		return height;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
}