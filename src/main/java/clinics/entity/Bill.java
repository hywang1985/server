package clinics.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bills", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "particulars", column = @Column(name = "particulars")),
		@AttributeOverride(name = "amount", column = @Column(name = "amount")),
		@AttributeOverride(name = "discounted", column = @Column(name = "discounted")),
		@AttributeOverride(name = "discount", column = @Column(name = "discount")),
		@AttributeOverride(name = "deletable", column = @Column(name = "deletable"))
})
public class Bill extends BaseEntity<Integer> {
	private String particulars;
	private Double amount;
	private Boolean discounted;
	private Double discount;
	private Boolean deletable;
	private Patient patient;
	private Visit visit;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public void setId(Integer id) {
		super.id = id;
	}

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

	@ManyToOne(optional = false)
	public Patient getPatient() {
		return patient;
	}

	@ManyToOne(optional = false)
	public Visit getVisit() {
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

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
}
