import java.util.Date;
import java.util.Calendar;
import java.lang.String;
import java.util.ArrayList;

public class Appointment {
	private int appoinmentId;
	private Clinic clinic;
	private Doctor doctor;
	private Calendar dateTime;
	private Service service;
	private Specialty specialty;
	private ArrayList<String> preAppointmentActions;
	private Patient patient;
	private TimeFrame timeFrame;
	
	public Appointment(int appoinmentId, Clinic clinic, Doctor doctor,
			Calendar dateTime, Specialty specialty, 
			ArrayList<String> preAppointmentActions, Patient patient, TimeFrame timeFrame) {
		this.appoinmentId = appoinmentId;
		this.clinic = clinic;
		this.doctor = doctor
;		this.dateTime = dateTime;
		this.specialty = specialty;
		this.service = service;
		this.patient = patient;
		this.preAppointmentActions = new ArrayList<String>();
		this.timeFrame = timeFrame;
	}

	public int getAppoinmentId() {
		return appoinmentId;
	}

	public void setAppoinmentId(int appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public ArrayList<String> getPreAppointmentActions() {
		return preAppointmentActions;
	}

	public void setPreAppointmentActions(ArrayList<String> preAppointmentActions) {
		this.preAppointmentActions = preAppointmentActions;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
