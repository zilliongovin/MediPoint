import java.util.ArrayList;

public class MedicalPractice {
	private String name;
	private ArrayList<Country> countries;
	private ArrayList<Doctor> doctors;
	private ArrayList<Specialty> specialties;
	
	public MedicalPractice(String name){
		this.name = name;
		this.countries = new ArrayList<Country>();
		this.specialties = new ArrayList<Specialty>();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean addCountry(Country country) {
		for (Country c: this.countries){
			if (c.getName().toLowerCase().equals(country.getName().toLowerCase())){
				System.out.println("Country already exists!");
				return false;
			}
		}		
		return this.countries.add(country);
	};
	
	public boolean removeCountry(Country country){
		for (Country c: countries){
			if(c.getName().toLowerCase().equals(country.getName().toLowerCase()))
				return countries.remove(c);
		}
		return false;
	};
	
	public Country findCountry(Country country){
		for (Country c: countries){
			if(c.getName().toLowerCase().equals(country.getName().toLowerCase()))
				return c;
		}
		return null;
	}
	
	public boolean addSpecialty(Specialty specialty) {
		for (Specialty s: specialties){
			if (s.getName().toLowerCase().equals(specialty.getName().toLowerCase())){
				System.out.println("Specialty "+ specialty.getName()+ " already exists!");
				return false;
			}
		}
		return specialties.add(specialty);
	}
	
	public boolean removeSpecialty(Specialty specialty){
		for (Specialty s: specialties){
			s.getName().toLowerCase().equals(specialty.getName().toLowerCase());
			return specialties.remove(s);
		}
		System.out.println("Specialty " + specialty.getName() + " does not exist!");
		return false;
	};
	
	public Specialty findSpecialty(Specialty specialty){
		for (Specialty s: specialties){
			s.getName().toLowerCase().equals(specialty.getName().toLowerCase());
			return s;
		}
		System.out.println("Specialty " + specialty.getName() + " does not exist!");
		return null;
	}
	
	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	public void printBranchAll(){
		ArrayList<Region> regs;
		
		System.out.println("================== Medical Practice MPAMS Branches ==================\n");
		if (countries.size() == 0){
			System.out.println("   No branch available.\n");
		}
		else{
			for (Country c: countries){
				System.out.println("===== "+ c.getName() + " =====");
				regs = c.getRegions();
				if (regs.size() == 0)
					System.out.println("   No branch available in this Country.\n");
				else{
					for (Region r: regs){
						r.printClinic();
					}
				}
			}
		}
	}
	
	public void printAllSpecialties(){
		System.out.println("====== List of Specialties ======\n");
		
		for (Specialty s: specialties){
			System.out.println(s.getName());
		}
		System.out.println();
	}
	
	public void printAllServices(){
		System.out.println("====== List of All Medical Services ======\n");
		
		for (Specialty s: specialties){
			s.printServices();
		}
	}
	
	public void printServiceBySpecialty(Specialty specialty){
		specialty.printServices();
	}
	
	public void printCountries(){
		for (Country c: countries){
			System.out.println(c.getName());
		}
	}
	
	public void printBranchByCountry(Country country){
		
	}
	
	public void printBranchByRegion(Region region){
		
	}
	
	public void printBranchBySpecialty(){
		
	}
}
