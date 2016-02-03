package clinics.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bills", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = "ID", insertable = false, updatable = false)),
		@AttributeOverride(name = "particulars", column = @Column(name = "particulars")),
		@AttributeOverride(name = "amount", column = @Column(name = "amount")),
		@AttributeOverride(name = "discounted", column = @Column(name = "discounted")),
		@AttributeOverride(name = "afterDiscount", column = @Column(name = "after_discount")),
		@AttributeOverride(name = "deletable", column = @Column(name = "deletable"))
})
public class Bill extends BaseEntity<Integer> {
	private String particulars;
	private Double amount;
	private Double discounted;
	private Double afterDiscount;
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

	public Double getDiscounted() {
		return discounted;
	}

	public Double getAfterDiscount() {
		return afterDiscount;
	}

	public Boolean getDeletable() {
		return deletable;
	}

	@OneToOne(optional = false)
	public Patient getPatient() {
		return patient;
	}

	@OneToOne(optional = false)
	public Visit getVisit() {
		return visit;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setDiscounted(Double discounted) {
		this.discounted = discounted;
	}

	public void setAfterDiscount(Double afterDiscount) {
		this.afterDiscount = afterDiscount;
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
