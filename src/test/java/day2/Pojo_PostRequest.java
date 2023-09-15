package day2;

/* To generate Setters and getters
 	String name;
	String Location;
	String phone;
	String Courses[];
	
	1. Select all and got to source and click on Generate Getters and Setters
 */


public class Pojo_PostRequest {
	
	String name;
	String Location;
	String phone;
	String Courses[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return Courses;
	}
	public void setCourses(String[] courses) {
		Courses = courses;
	}


}
