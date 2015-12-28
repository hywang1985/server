package clinics.model;

import java.util.Date;

public class VisitModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362208769594760467L;

	private String roomNumber;

	private Date admitDate;

	private Date dischargeDate;

	private int referredBy;

	private String reason;

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public int getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(int referredBy) {
		this.referredBy = referredBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
