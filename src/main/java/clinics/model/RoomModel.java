package clinics.model;

import java.util.ArrayList;
import java.util.List;

import clinics.model.meta.RoomEquipmentModel;

public class RoomModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private String name;

	private String description;

	private Integer occupancy;

	private Boolean allotable;
	
	private List<RoomEquipmentModel> equipments = new ArrayList<RoomEquipmentModel>();

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public Boolean getAllotable() {
		return allotable;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}

	public void setAllotable(Boolean allotable) {
		this.allotable = allotable;
	}

	public List<RoomEquipmentModel> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<RoomEquipmentModel> equipments) {
		this.equipments = equipments;
	}

}