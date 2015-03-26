import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Day{
	private Map <Integer,String> map;
	private String name;
	
	public Day(int i){
		Map <Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "Sunday");
		map.put(2, "Monday");
		map.put(3, "Tuesday");
		map.put(4, "Wednesday");
		map.put(5, "Thursday");
		map.put(6, "Friday");
		map.put(7, "Saturday");
		this.name = map.get(i);
	}
	public void setDay(int i){
		this.name = map.get(i);
	}
	public String getDay(){
		return name;
	}

}