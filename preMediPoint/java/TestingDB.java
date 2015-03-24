import java.util.*;
import java.lang.String;

public class TestingDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		char ch;
		int n;
		String name, address, region;
		
		ArrayList<Specialty> specialties = new ArrayList<Specialty>();
		ArrayList<Doctor> doctors = new ArrayList<Doctor>();
		//Adding country
		/*System.out.println("Enter name of country: ");
		String countryName = input.next();
		Country c = new Country(countryName);*/
		/*
		do {
			System.out.println("Do you want to add branch to this country? Y or N");
			ch = input.next().charAt(0);
		} while(Character.toLowerCase(ch) != 'n');
		System.out.println("Please enter the number of clinic you want to add to this country:");
		n = input.nextInt();
		for (int i=0; i < n; i++){
			System.out.println("Enter Clinic region: ");
			region = input.next();
			System.out.println("Enter Clinic name: ");
			name = input.next();
			System.out.println("Enter Clinic address: ");
			address = input.next();
			System.out.println("Choose Clinic Specialties: ");
			
			System.out.println("Choose Clinic Doctors: ");
		}*/
		//Hardcoded
		
		//Create Medical Practice
		MedicalPractice mp = new MedicalPractice("Private Medical Practice Healthier");
		mp.printBranchAll();
		mp.printCountries();
		
		//Create Country
		//IMPT: every Country created must be added to Medical Practice
		Country malay = new Country("Malaysia", mp);
		Country thai = new Country("Thailand", mp);
		Country sing = new Country("Singapore", mp);
		
		mp.printBranchAll();
		
		//Create Region
		//IMPT: every region created must be added to Country
		Region boonlay = new Region("Boonlay", sing);
		mp.printBranchAll();
		
		//Create Specialty
		Specialty ENT = new Specialty("ENT", mp);
		
		//Add Services to the Specialty, here assumed every specialty will have 
		//same services accross all medical practice
		ENT.addService(new Service("Allergy Testing and Treatment"));
		ENT.addService(new Service("Auditory Verbal Therapy"));
		ENT.addService(new Service("Ear (Otology) and Dizziness"));
		ENT.addService(new Service("Hearing Test"));
		ENT.addService(new Service("Kids (Paediatric) ENT"));
		ENT.addService(new Service("Multi-disciplinary Tumour Clinic"));
		ENT.addService(new Service("Nasal and Sinus Surgery"));
		ENT.addService(new Service("Obstructive Sleep Apnoea Assessment and Surgery"));
		ENT.addService(new Service("Speech Therapy"));
		ENT.addService(new Service("Thyroid Removal"));
		//ENT.addService(new Service("Thyroid Removal"));
		//ENT.printServices();
		
		Specialty dental = new Specialty("Dental Service", mp);
		
		//dental.printServices();
		Specialty womenHealth = new Specialty("Women Health Services", mp);
		//womenHealth.printServices();
		
		//Printing out
		//mp.printAllSpecialties();
		//mp.printAllServices();
	
		//Create Clinic
		Clinic abc = new Clinic("ABC Clinic", "Healthy Street 101", boonlay, 623326, 655555, "abclinic@MP.com");
		System.out.println(abc);
		//mp.printBranchAll();
		
		//Create Doctor		
		Doctor doc = new Doctor("A1", "Melissa",ENT,10 );
		System.out.println(doc);
		
		//Create DoctorSchedule
		DoctorSchedule ds = new DoctorSchedule(abc, new Day(2), new TimeFrame(18, 26));
		doc.printDoctorSchedule();
		doc.addDoctorSchedule(ds);
		doc.printDoctorSchedule();
		
		//Create Patient
		Patient p = new Patient("angkasa10", "iluvsky", "Angkasa Raya", "A123", 
				"angkasa10@gmail.com", 991122, "Male", "Skyline View 10", 
				"single", "Singaporean", sing, "p1", 19);
		//Check doctor schedule
		//check patient appointment
		//p.addAppointment(appointment);
		
		//Create Treatment
		
		//Print List of Medical Practice Branch (all 3 countries)
		//Print List of Medical Practice by Country
		//Print List of Medical Practice by Region
		//Print List of Medical Practice by Specialty
		//Print List of Appointment
		//Print Account Details
		//Print List of Patient Details
		//Print List of Doctor Details
		
	}

}
