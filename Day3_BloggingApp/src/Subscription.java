import java.util.*;

public class Subscription {
	private Long id;
	private String tag;
	private Set<Long> subbedUsers = new HashSet<>();
	private Set<Long> subbedGroups = new HashSet<>();
	
	Subscription(String _tag, Long _id){
		tag = _tag;
		id = _id;
	}
	
	public Long getId(){
		return id;
	}
	
	public String getTag(){
		return tag;
	}
	
	public void addUser(Long id){
		subbedUsers.add(id);
	}
	
	public void addGroup(Long id){
		subbedGroups.add(id);
	}
	
	public void removeUser(Long id){
		subbedUsers.remove(id);
	}
	
	public void removeGroup(Long id){
		subbedGroups.remove(id);
	}
	
	public Long[] getUsers(){
		return (Long[])subbedUsers.toArray();
	}
	
	public Long[] getGroups(){
		return (Long[])subbedGroups.toArray();
	}
}
