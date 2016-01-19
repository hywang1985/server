package clinics.model;


public class AppointmentModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5257830650224317268L;

	private String dateTime;
	private Integer department;
	private Integer doctor;
	private String name;
	private Integer patient;
	private String reason;
	private Boolean done;

	public String getDateTime() {
		return dateTime;
	}

	public Integer getDepartment() {
		return department;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public String getName() {
		return name;
	}

	public Integer getPatient() {
		return patient;
	}

	public String getReason() {
		return reason;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setDepartment(Integer department) {
		this.department = department;
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

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
}
