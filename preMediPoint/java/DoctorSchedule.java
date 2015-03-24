import java.util.Calendar;
import java.util.HashMap;

public class DoctorSchedule {
	private Clinic clinic;
	private Day day;
	private TimeFrame timeFrame;
	
	public DoctorSchedule(Clinic clinic, Day day, TimeFrame timeFrame) {
		this.clinic = clinic;
		this.day = day;
		this.timeFrame = timeFrame;
	}
	
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public String getDay() {
		return day.getDay();
	}
	public void setDay(Day day) {
		this.day = day;
	}
	
	public String toString(){
		return "Clinic: " + this.clinic.getName() + "\n" + 
			   "Day: " + this.day.getDay() + "\n" +
			   "Time: " + String.format("%02d", this.timeFrame.getStartHour()) + ":" + String.format("%02d", this.timeFrame.getStartMinute()) + 
				"-" + String.format("%02d", this.timeFrame.getEndHour()) + ":" + String.format("%02d", this.timeFrame.getEndMinute());
	}
}
