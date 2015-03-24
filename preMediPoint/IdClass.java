public class IdClass {
	private static int id=0;
	private String patientId;
	private String name;
	
	public IdClass(String name){
		this.patientId = "P" + String.format("%07d", id);
		this.name = name;
		id++;
	}
	
	
	public String toString(){
		return "Patient Id: " + this.patientId + "\nName: " + this.name;
	}
}
