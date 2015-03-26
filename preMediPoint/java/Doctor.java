import java.lang.String;
import java.util.ArrayList;

public class Doctor {	
	private String doctorId;
	private String name;
	private Specialty specialization;
	private int practiceDuration;
	//per clinic
	private ArrayList<DoctorSchedule> doctorSchedules;
	private ArrayList<Appointment> appointments;
	
	public Doctor(String doctorId, String name, Specialty specialization,
			int practiceDuration) {
		this.doctorId = doctorId;
		this.name = name;
		this.specialization = specialization;
		this.practiceDuration = practiceDuration;
		this.appointments = new ArrayList<Appointment>();
		this.doctorSchedules = new ArrayList<DoctorSchedule>();
	}
	
	public String getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Specialty getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialty specialization) {
		this.specialization = specialization;
	}
	public int getPracticeDuration() {
		return practiceDuration;
	}
	public void setPracticeDuration(int practiceDuration) {
		this.practiceDuration = practiceDuration;
	}
	public ArrayList<DoctorSchedule> getDoctorSchedule() {
		return doctorSchedules;
	}
	public boolean addDoctorSchedule(DoctorSchedule doctorSchedule) {
		for (DoctorSchedule d: doctorSchedules){
			if(d.getDay().equalsIgnoreCase(doctorSchedule.getDay())){
				System.out.println("Day clashes");
				return false;
			}
		}
		return doctorSchedules.add(doctorSchedule);
	}
	
	public void printDoctorSchedule(){
		if (doctorSchedules.isEmpty())
			System.out.println("No practice schedule");
		else{
			for (DoctorSchedule ds: doctorSchedules){
				System.out.println(ds);
			}
		}
	}

	public String toString(){
		return 	"=== Printing Doctor Info ==="+"\n"+
				"ID: " + this.doctorId + "\n" +
				"Name: " + this.name + "\n" +
				"Practice Duration: " + this.practiceDuration+ "\n" + 
				"Specialization: " + this.specialization.getName() + "\n"
				;
	}
}
