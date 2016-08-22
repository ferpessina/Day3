import java.util.*;

public class Group {
	private Long id;
	private String name;
	private Set<Long> users = new HashSet<Long>();
	
	Group(String _name, Long _id){
		this.name = _name;
		this.id = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String _name){
		name = _name;
	}
	
	public void addUser(Long userId){
		users.add(userId);
	}
	
	public void removeUser(Long userId){
		users.remove(userId);
	}
	
	public Long[] getUsers(){
		return (Long[]) users.toArray();
	}
}
