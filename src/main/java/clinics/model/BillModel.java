package clinics.model;

public class BillModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private String particulars;
	private Double amount;
	private Boolean discounted;
	private Double discount;
	private Boolean deletable;
	private Integer patient;
	private Integer visit;

	public String getParticulars() {
		return particulars;
	}

	public Double getAmount() {
		return amount;
	}

	public Boolean getDiscounted() {
		return discounted;
	}

	public Double getDiscount() {
		return discount;
	}

	public Boolean getDeletable() {
		return deletable;
	}

	public Integer getPatient() {
		return patient;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setDiscounted(Boolean discounted) {
		this.discounted = discounted;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	public void setPatient(Integer patient) {
		this.patient = patient;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

}