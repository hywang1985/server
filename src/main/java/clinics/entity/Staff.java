package clinics.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import clinics.enums.Gender;
import clinics.enums.Prefix;

@Entity
@Table(name = "staffs", uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false)),
        @AttributeOverride(name = "firstName", column = @Column(name = "firstName")),
        @AttributeOverride(name = "lastName", column = @Column(name = "lastName")),
        @AttributeOverride(name = "mobile", column = @Column(name = "mobile")),
        @AttributeOverride(name = "address", column = @Column(name = "address")),
        @AttributeOverride(name = "dob", column = @Column(name = "dob")),
        @AttributeOverride(name = "doj", column = @Column(name = "doj")),
        @AttributeOverride(name = "age", column = @Column(name = "age")),
        @AttributeOverride(name = "gender", column = @Column(name = "gender")),
        @AttributeOverride(name = "prefix", column = @Column(name = "prefix")),
        @AttributeOverride(name = "bloodGroup", column = @Column(name = "bloodgroup")),
        @AttributeOverride(name = "createdDate", column = @Column(name = "create_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "update_date")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "user_entered")),
        @AttributeOverride(name = "modifiedBy", column = @Column(name = "user_updated"))
})
public class Staff extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 4289151143888117381L;

	private String firstName;

	private String lastName;

	private String mobile;

	private String address;

	private String dob;
	
	private String doj;

	private byte age;

	private Gender sex;

	private Prefix prefix;

	private int bloodGroup;

	private Date createdDate;

	private Date modifiedDate;

	private int createdBy;

	private int modifiedBy;
	
	private Set<StaffDepartment> staffDepartments;
	
	public Staff() {
		staffDepartments = new HashSet<StaffDepartment>();
	}
	
	public void addDepartment(Department department) {
		StaffDepartment staffDepartment = new StaffDepartment();
		staffDepartment.setStaff(this);
		staffDepartment.setDepartment(department);
		staffDepartments.add(staffDepartment);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public String getDob() {
		return dob;
	}

	public byte getAge() {
		return age;
	}

	public Gender getSex() {
		return sex;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

	public int getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(int bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
		Staff rhs = (Staff) obj;
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

	@OneToMany(mappedBy = "id.staff", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<StaffDepartment> getStaffDepartments() {
		return staffDepartments;
	}

	public void setStaffDepartments(Set<StaffDepartment> staffDepartments) {
		this.staffDepartments = staffDepartments;
	}

	public void updateDepartment(StaffDepartment curr) {
		curr.setStaff(this);
		staffDepartments.add(curr);
	}
}
