import java.lang.String;
import java.util.ArrayList;

public class Specialty {
	private String name;
	private ArrayList<Service> services;
	
	public Specialty(String name, MedicalPractice medicalPractice){
		this.name = name;
		this.services = new ArrayList<Service>();
		medicalPractice.addSpecialty(this);
		addService(new Service("General Consultation"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public boolean addService(Service service) {
		for (Service s: services){
			if (s.getName().toLowerCase().equals(service.getName().toLowerCase())){
				System.out.println("Service "+ service.getName()+ " already exists!");
				return false;
			}
		}
		return services.add(service);
	}
	
	public boolean removeService(Service service){
		for (Service s: services){
			s.getName().toLowerCase().equals(service.getName().toLowerCase());
			return services.remove(s);
		}
		return false;
	};
	
	public Service findService(Service service){
		for (Service s: services){
			s.getName().toLowerCase().equals(service.getName().toLowerCase());
			return s;
		}
		return null;
	}
	
	public void printServices(){
		System.out.println("-- "+name+" --");
		
		for (Service s: services){
			System.out.println(s.getName());
		}
		System.out.println();
	}
	
}
