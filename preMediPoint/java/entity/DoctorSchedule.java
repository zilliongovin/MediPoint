import java.util.Calendar;
import java.util.HashMap;

public class DoctorSchedule {
	private Clinic clinic;
	private Doctor doctor;
	private Specialty specialty;
	private boolean occupied=false;
	private Calendar day;
	private int startTime;
	private int endTime;
	
	public DoctorSchedule(Clinic clinic, Specialty specialty, Calendar day, int startTime, int endTime) {
		this.clinic = clinic;
		this.specialty = specialty;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public Calendar getDay() {
		return day;
	}
	public void setDay(Calendar day) {
		this.day = day;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		this.occupied = true;
		doctor.addDoctorSchedule(this);
	}
	
	public void removeDoctor(){
		this.doctor = null;
		this.occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
}
