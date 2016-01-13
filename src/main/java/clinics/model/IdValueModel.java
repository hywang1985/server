package clinics.model;

public class IdValueModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340859196299627969L;

	private Integer value;

	public IdValueModel() {
	}

	public IdValueModel(Integer id, Integer value) {
		super();
		this.id = id;
		this.value = value;
	}

	public IdValueModel(Integer id) {
		super();
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}