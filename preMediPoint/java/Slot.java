import java.util.Calendar;

public class Slot {
	public Calendar dayDate;
	public Calendar timeDate;
	public boolean available;
	
	public Slot(Calendar dayDate, Calendar timeDate, boolean available) {
		this.dayDate = dayDate;
		this.timeDate = timeDate;
		this.available = false;
	}
	
	public void setTimeDate(Calendar timeDate) {
		this.timeDate = timeDate;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
