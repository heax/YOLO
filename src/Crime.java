import java.util.StringTokenizer;


public class Crime {
	private String location;
	private String description;
	
	public String getDescrption() {
		return description;
	}

	public void setDescrption(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public StringTokenizer getCrimeDescription(){	
		System.out.println(description);
		StringTokenizer st2 = new StringTokenizer(description, ",");
		 return st2;
	
	}
	
	public StringTokenizer getCrimeLocation(){
		StringTokenizer st2 = new StringTokenizer(location, ",");
		return st2;
	}
	


	

	public Crime() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */

}
