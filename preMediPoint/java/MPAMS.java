import java.util.Scanner;

public class MPAMS {
	public static void println(Object o){
		System.out.println(o);
	}
	
	public static void main(String[] args) {
		int op;
		Scanner in = new Scanner(System.in);
		println(""
				+ "================== Medical Practice Appointment System ==================");
		do{
			println("Please choose an option\n"
					+ "1: Admin\n"
					+ "2: User\n"
					+ "Your option: ");
			op = in.nextInt();
			switch(op){
				case 1:
					//Admin
					System.out.println("I am Admin");
					//Create Specialty
					//Create Country
					//Create Clinic
					//Create MedicalPractice
					//Create Patient
					//Create Doctor
					//Create DoctorSchedule
					//Create Treatment
					//Print List of Appointment
					//Print Account Details
					//Print List of Patient Details
					//Print List of Doctor Details
					break;
				case 2: System.out.println("I am User"); break;
					//Create User
					//Simple LoginLogout Logic 
					//Print Doctor Schedule
					//Create Appointment
					//Change Appointment
					//Cancel Appointment
				default: System.out.println("Exitting program...");
			}
		} while(op > 0 && op < 3);
		println("Program Exit");
	}

}
