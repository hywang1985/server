package clinics.model;

import clinics.enums.Gender;
import clinics.enums.Prefix;

public class PersonModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -420310631229478429L;

	private String address;

	private byte age;

	private int bloodGroup;

	private String dob;

	private String firstName;

	private String lastName;

	private String mobile;

	private Prefix prefix;
	
	private Gender sex;

	public String getAddress() {
		return address;
	}

	public byte getAge() {
		return age;
	}

	public int getBloodGroup() {
		return bloodGroup;
	}

	public String getDob() {
		return dob;
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

	public Prefix getPrefix() {
		return prefix;
	}

	public Gender getSex() {
		return sex;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setBloodGroup(int bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}
}
