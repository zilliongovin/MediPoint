public class Service {
	private int serviceId=0;
	private String name;
	private int duration;
	
	public Service(String name) {
		this.serviceId++;
		this.name = name;
		
		//The regular timing is 30 minutes
		this.duration = 30;
	}
	public Service(String name, int duration) {
		this.serviceId++;
		this.name = name;
		this.duration = duration;
	}
		
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
