import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.String;

public class Region {
	private String name;
	private Country country;
	private ArrayList<Clinic> clinics;
	
	public Region(String name, Country country){
		this.name = name;
		this.country = country;
		this.country.addRegion(this);
		this.clinics = new ArrayList<Clinic>(); 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public ArrayList<Clinic> getClinics() {
		return clinics;
	}
	
	public boolean addClinic(Clinic clinic) {
		for (Clinic c: clinics){
			if(c.getName().toLowerCase().equals(clinic.getName().toLowerCase())){
				System.out.println("Clinic already exists!");
				return false;
			}
		}
		return clinics.add(clinic);
	};
	
	public boolean removeClinic(Clinic clinic){
		for (Clinic c: clinics){
			if(c.getName().toLowerCase().equals(clinic.getName().toLowerCase()))
				return clinics.remove(c);
		}
		return false;
	};
	
	public Clinic findClinic(Clinic clinic){
		for (Clinic c: clinics){
			if(c.getName().toLowerCase().equals(clinic.getName().toLowerCase()))
				return c;
		}
		return null;
	}

	public void printClinic(){
		//Collections.sort(clinics, ClinicRegionComparator);
		System.out.println("   " + name);
		if (clinics.size()==0)
			System.out.println("      No branch available in this Region.\n");
		else{
			int i = 1;
			for (Clinic c: clinics){
				System.out.print("      " + i++ + ". ");
				System.out.println(c);
			}
		}
	}
	
	/*public static Comparator<Clinic> ClinicRegionComparator = new Comparator<Clinic>(){
		public int compare(Clinic clinic, Clinic anotherClinic) {
			String region1 = clinic.getRegion().toLowerCase();
			String region2 = anotherClinic.getRegion().toLowerCase();
			
			return region1.compareTo(region2);
		}
	}*/
	public String toString(){
		return name + ", " + country.getName();
	}
}
