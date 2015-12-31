package clinics.model;

public class EquipmentModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private String name;

	private String description;

	private Boolean working;

	private Boolean common;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getWorking() {
		return working;
	}

	public Boolean getCommon() {
		return common;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public void setCommon(Boolean common) {
		this.common = common;
	}
}