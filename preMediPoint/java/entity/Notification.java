import java.util.Calendar;

public class Notification {
	private int id;
	private Patient recipient;
	private Calendar timeDate;
	private Appointment appointment;
	private String message;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient getRecipient() {
		return recipient;
	}
	public void setRecipient(Patient recipient) {
		this.recipient = recipient;
	}
	public Calendar getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(Calendar timeDate) {
		this.timeDate = timeDate;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
