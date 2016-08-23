import java.util.*;

public class TagSub {
	private Long id;
	private String tag;
	private Set<User> subbedUsers = new HashSet<>();
	private Set<Group> subbedGroups = new HashSet<>();
	
	TagSub(String _tag, Long _id){
		tag = _tag;
		id = _id;
	}
	
	public Long getId(){
		return id;
	}
	
	public String getTag(){
		return tag;
	}
	
	public void addUser(User user){
		subbedUsers.add(user);
	}
	
	public void addGroup(Group group){
		subbedGroups.add(group);
	}
	
	public void removeUser(User user){
		subbedUsers.remove(id);
	}
	
	public void removeGroup(Group group){
		subbedGroups.remove(id);
	}
	
//	public User[] getUsers(){
//		return (Long[])subbedUsers.toArray();
//	}
//	
//	public Long[] getGroups(){
//		return (Long[])subbedGroups.toArray();
//	}
}
