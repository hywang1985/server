package clinics.model;

import java.util.ArrayList;
import java.util.List;

public class RoomModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private Boolean allotable;

	private String description;

	private List<IdValueModel> equipments = new ArrayList<IdValueModel>();

	private String name;
	
	private Integer occupancy;
	
	private Boolean occupied;
	
	private Integer available;

	public Boolean getAllotable() {
		return allotable;
	}

	public String getDescription() {
		return description;
	}

	public List<IdValueModel> getEquipments() {
		return equipments;
	}

	public String getName() {
		return name;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setAllotable(Boolean allotable) {
		this.allotable = allotable;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEquipments(List<IdValueModel> equipments) {
		this.equipments = equipments;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}