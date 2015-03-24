import java.util.Calendar;
import java.util.Date;
import java.text.DateFormatSymbols;
import java.time.*;
import java.util.GregorianCalendar;

public class TimeDateTest {
	
	public static void main(String[] args){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(cal);
		System.out.println(day);
		
		  int year;
	      // Create a Gregorian calendar initialized
	      // with the current date and time in the
	      // default locale and timezone.
	      GregorianCalendar gcalendar = new GregorianCalendar();
		
	    cal.get(Calendar.DATE);
	    
	    
	    System.out.print("Date: ");
	    //System.out.print(months[gcalendar.get(Calendr.MONTH)]);
	    System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
	    System.out.println(year = gcalendar.get(Calendar.YEAR));
	    System.out.print("Time: ");
	    System.out.print(gcalendar.get(Calendar.HOUR) + ":");
	    System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
	    System.out.println(gcalendar.get(Calendar.SECOND));
	    
	    
	    String dayNames[] = new DateFormatSymbols().getWeekdays();
	    //Calendar date2 = Calendar.getInstance();
	    //System.out.println("Today is a " 
	      //  + dayNames[date2.get(Calendar.DAY_OF_WEEK)]);
	    for (String dN: dayNames){
	    	System.out.println(dN);
	    }
	    
	    System.out.println(gcalendar.getTime());
	    Calendar future = Calendar.getInstance();
	    future.set(Calendar.DAY_OF_WEEK, 2);
	    System.out.println(future.getTime());
	    future.set(Calendar.DAY_OF_WEEK_IN_MONTH, 0);
	    System.out.println(future.getTime());
	    future.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
	    System.out.println(future.getTime());
	    future.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
	    System.out.println(future.getTime());
	    //System.out.println(gcalendar.after(gcalendar.))
	}
	
	public String getDate(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		
		return "date";
	}
	
}
