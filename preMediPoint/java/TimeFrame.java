public class TimeFrame {
	
	private int start;
	private int end;
	
	public TimeFrame(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public int getStartHour(){
		return start/2;
	}
	
	public int getStartMinute(){
		return (start % 2)*30;
	}
	
	public int getEndHour(){
		return end/2;
	}
	
	public int getEndMinute(){
		return (end % 2)*30;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
