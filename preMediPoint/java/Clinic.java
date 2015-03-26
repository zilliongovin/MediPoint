import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Clinic {
	private String name;
	private String address;
	private Region region;
	private int zipCode;
	private int telNumber;
	private int faxNumber;
	private String email;
	//private HashMap<Calendar, Calendar> openTiming;
	private ArrayList<DoctorSchedule> doctorSchedules;
	private ArrayList<Specialty> specialties;
	private ArrayList<Doctor> doctors;
	
	public Clinic(String name, String address, Region region, int zipCode, int telNumber, String email){
		this.name = name;
		this.address = address;
		this.region = region;
		this.zipCode = zipCode;
		this.telNumber = telNumber;
		this.email = email;
		this.specialties = new ArrayList<Specialty>();
		//this.openTiming = new HashMap<Calendar, Calendar>();
		this.doctorSchedules = new ArrayList<DoctorSchedule>();
		this.doctors = new ArrayList<Doctor>();
		region.addClinic(this);
	}
	
	public Clinic(String name, String address, Region region, int zipCode, int telNumber, int faxNumber, String email){
		this.name = name;
		this.address = address;
		this.region = region;
		this.zipCode = zipCode;
		this.telNumber = telNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.specialties = new ArrayList<Specialty>();
		//this.openTiming = new HashMap<Calendar, Calendar>();
		this.doctorSchedules = new ArrayList<DoctorSchedule>();
		this.doctors = new ArrayList<Doctor>();
		region.addClinic(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public int getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(int telNumber) {
		this.telNumber = telNumber;
	}
	public int getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(int faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Specialty> getSpecialties() {
		return specialties;
	}
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	
	public boolean addSpecialty(Specialty specialty) {
		for (Specialty s: specialties){
			if (s.getName().toLowerCase().equals(specialty.getName().toLowerCase())){
				System.out.println("Specialty "+ specialty.getName()+ " already exists!");
				return false;
			}
		}
		//doctorschedule and specialties.add(specialty) succeed then return true
		
		//if (addDoctorSchedule(specialty))
		//	if (specialties.add(specialty))
		//		return true;
		//else
		//	return false;
		//
		
		return specialties.add(specialty);
	}
	
	public boolean addDoctorSchedule(Specialty specialty){
		//day = monday;
		
		//for (day = monday; day <= saturday; day++){
		//	for (startTime = 9; startTime <= 17; startTime+=3){
		//		DoctorSchedule ds = new DoctorSchedule(this.clinic, specialty, day, startTime, startTime + 4);
		//		if (!(doctorSchedules.add(ds))
		//			return false;
		//	}
		//} 
		return true;
	}
	
	public boolean removeSpecialty(Specialty specialty){
		for (Specialty s: specialties){
			s.getName().toLowerCase().equals(specialty.getName().toLowerCase());
			//if (removeDoctorSchedule)
			//	if specialties.remove(s)
			//		return true;
			//else
			//		return false;
			return specialties.remove(s);
		}
		System.out.println("Specialty " + specialty.getName() + " does not exist!");
		return false;
	};
	
	public boolean removeDoctorSchedule(Specialty specialty){
		//for (DoctorSchedule ds: doctorSchedules){
			//if (ds.getSpecialty().equals(specialty))
				//if (!doctorSchedules.remove(ds))
					//return false;
		//}
		return true;
	}
	
	public Specialty findSpecialty(Specialty specialty){
		for (Specialty s: specialties){
			s.getName().toLowerCase().equals(specialty.getName().toLowerCase());
			return s;
		}
		System.out.println("Specialty " + specialty.getName() + " does not exist!");
		return null;
	}
	
	public boolean findDoctorSchedule(){
		return true;
	}
	
	public String toString(){
		String tabSpace = "      ";
		if (faxNumber == 0)
			return this.name + "\n" +
				   tabSpace + "Address: " + this.address + ", " + this.region + ". " + this.zipCode + ".\n"+
				   tabSpace + "Tel: " + this.telNumber + " Fax: - \n"+
				   tabSpace + "Email: " + this.email + "\n";
		else
			return this.name + "\n" +
				   tabSpace + "Address: " + this.address + ", " + this.region + ". " + this.zipCode + ".\n"+
				   tabSpace + "Tel: " + this.telNumber + " Fax: " + this.faxNumber + "\n"+
				   tabSpace + "Email: " + this.email + "\n";
	}

}
