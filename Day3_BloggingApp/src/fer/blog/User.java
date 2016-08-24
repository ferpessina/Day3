
public class User {
	private Long userId;
	private String name;
	private String email;
	
	User(String _name, String _email, Long _id){
		this.name = _name;
		this.email = _email;
		this.userId = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public Long getId(){
		return userId;
	}
	
	public void setName(String _name){
		this.name = _name;
	}
	
	public void setEmail(String _email){
		this.email = _email;
	}
	
	public void printInfo(){
		System.out.println("Name: "+name+"\nEmail:"+email);
	}
}
