import java.util.StringTokenizer;


public class Crime {
	//en klass för att sortera informaionen som hämtas från rss-en
	
	
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
	
	//Två metoder för att göra radbyten istället för kommatecken i texten så att det ska se snyggare ut
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
