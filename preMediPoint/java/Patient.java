import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;

public class Patient extends Account{
	private String patientId;
	private ArrayList<String> allergy;
	private int age;
	private ArrayList<Appointment> listOfAppointments;
	private ArrayList<Treatment> listOfTreatments;
	private Notifier notifier;
	
	public Patient(
			String username, String password, String name, String nric,
			String email, int phoneNumber, String gender, String address, String maritalStatus,
			String citizenship, Country countryOfResidence,
			String patientId, int age) {
	
		super(username, password, name, nric, email, phoneNumber, 
	       gender, address, maritalStatus,citizenship, countryOfResidence);
		this.patientId = patientId;
		this.age = age;
		this.notifier = new Notifier(this);
		this.listOfAppointments = new ArrayList<Appointment>();
		this.listOfTreatments = new ArrayList<Treatment>();
		this.allergy = new ArrayList<String>();
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public ArrayList<String> getAllergy() {
		return allergy;
	}
	public void setAllergy(ArrayList<String> allergy) {
		this.allergy = allergy;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ArrayList<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}
	public boolean addAppointment(Appointment appointment) {
		//appointment that the user chose is the one that is free (already check with the doctor)
		//1. checkAppointment
		//	for the list of appointment that the patient has, check if the day and time are the same as the appointment
		
		return false;
	}
	public ArrayList<Treatment> getListOfTreatments() {
		return listOfTreatments;
	}
	public void setListOfTreatments(ArrayList<Treatment> listOfTreatments) {
		this.listOfTreatments = listOfTreatments;
	}
	public Notifier getNotifier() {
		return notifier;
	}
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}
}
