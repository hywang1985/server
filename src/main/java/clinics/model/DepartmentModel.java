package clinics.model;

public class DepartmentModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private String name;

	private String description;
	
	private Integer chief;
	
	private Boolean appointmentable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getChief() {
		return chief;
	}

	public void setChief(Integer chief) {
		this.chief = chief;
	}

	public Boolean getAppointmentable() {
		return appointmentable;
	}

	public void setAppointmentable(Boolean appointmentable) {
		this.appointmentable = appointmentable;
	}
}
