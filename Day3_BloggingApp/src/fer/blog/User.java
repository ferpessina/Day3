package fer.blog;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Long userId;
	private String name;
	private String email;
	private Set<Group> groups = new HashSet<>();
	
	User(String _name, String _email, Long _id){
		if(_name.equals(""))
			throw new IllegalArgumentException("User name can't be empty");
		name = _name;
		
		if(_email.equals(""))
			throw new IllegalArgumentException("User email can't be empty");
		email = _email;

		this.userId = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void joinGroup(Group group){
		if(group != null){
			groups.add(group);
		}
	}
	
	public void leaveGroup(Group group){
		if(group != null){
			groups.remove(group);
		}
	}
	
	public Set<Group> getGroups(){
		return groups;
	}
	
	public Long getId(){
		return userId;
	}
	
	public void setName(String _name){
		if(_name.equals(""))
			throw new IllegalArgumentException("User name can't be empty");
		name = _name;	
	}
	
	public void setEmail(String _email){
		if(_email.equals(""))
			throw new IllegalArgumentException("User email can't be empty");
		email = _email;		
	}
	
	public void printInfo(){
		System.out.println("Name: "+name+"\nEmail: "+email);
	}
}
