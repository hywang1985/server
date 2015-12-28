package clinics.model;

import java.util.Date;

public class AppointmentModel extends PersonModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5257830650224317268L;

	private Date date;

	private Date time;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
