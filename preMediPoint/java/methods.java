private id = 0;
private String patientId;

public constructor(){
	patientId = "P" + String.format("%6d", id);
}
public static main(){
	
}

public String toString(){
	return patientId;
}