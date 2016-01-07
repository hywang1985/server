package clinics.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import clinics.enums.VisitType;

@Entity
@Table(name = "VISITS", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false)),
        @AttributeOverride(name = "patientId", column = @Column(name = "patient_id")),
        @AttributeOverride(name = "reason", column = @Column(name = "reason")),
        @AttributeOverride(name = "weight", column = @Column(name = "weight")),
        @AttributeOverride(name = "height", column = @Column(name = "height")),
        @AttributeOverride(name = "respiRate", column = @Column(name = "respi_rate")),
        @AttributeOverride(name = "sBp", column = @Column(name = "systolic_bp")),
        @AttributeOverride(name = "dBp", column = @Column(name = "diastolic_bp")),
        @AttributeOverride(name = "bodyTemp", column = @Column(name = "body_temp")),
        @AttributeOverride(name = "type", column = @Column(name = "visit_type")),
        @AttributeOverride(name = "room", column = @Column(name = "room")),
        @AttributeOverride(name = "visitDate", column = @Column(name = "visit_date")),
        @AttributeOverride(name = "dischargeDate", column = @Column(name = "discharge_date")),
        @AttributeOverride(name = "referredBy", column = @Column(name = "referred_by")),
        @AttributeOverride(name = "createdDate", column = @Column(name = "create_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "update_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "user_entered")),
        @AttributeOverride(name = "modifiedBy", column = @Column(name = "user_updated"))
})
public class Visit extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;
	
	private Integer patientId;

	private Double weight;

	private Double height;

	private Integer respiRate;

	private Integer sBp;

	private Integer dBp;

	private Integer bodyTemp;

	private VisitType type;

	private Integer room;

	private String visitDate;

	private String dischargeDate;

	private Integer referredBy;

	private String reason;

	private Date createdDate;

	private Date modifiedDate;

	private int createdBy;

	private int modifiedBy;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getRespiRate() {
		return respiRate;
	}

	public void setRespiRate(Integer respiRate) {
		this.respiRate = respiRate;
	}

	public Integer getsBp() {
		return sBp;
	}

	public void setsBp(Integer sBp) {
		this.sBp = sBp;
	}

	public Integer getdBp() {
		return dBp;
	}

	public void setdBp(Integer dBp) {
		this.dBp = dBp;
	}

	public Integer getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Integer bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public VisitType getType() {
		return type;
	}

	public void setType(VisitType type) {
		this.type = type;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Integer getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(Integer referredBy) {
		this.referredBy = referredBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return (new HashCodeBuilder()).append(this.id).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit rhs = (Visit) obj;
		return (new EqualsBuilder()).append(this.id, rhs.id).isEquals();
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
