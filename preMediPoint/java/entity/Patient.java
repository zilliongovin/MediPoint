import java.lang.String;
import java.util.ArrayList;

public class Patient {
	private String patientId;
	private ArrayList<String> allergy;
	private int age;
	private ArrayList<Appointment> listOfAppointments;
	private ArrayList<Treatment> listOfTreatments;
	private Notifier notifier;
	
	
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
	public void setListOfAppointments(ArrayList<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
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
