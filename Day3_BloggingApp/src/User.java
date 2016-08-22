import java.util.*;

public class User {
	private Long userId;
	private String name;
	private String email;
	private Set<Long> posts = new HashSet<Long>();
	private Set<Long> groups = new HashSet<Long>();
	
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
	
	public Long[] getPostIds(){
		return (Long[]) posts.toArray();
	}
	public void addPost(Long id){
		posts.add(id);
	}
	public void removePost(Long id){
		posts.remove(id);
	}
	public void addGroup(Long id){
		groups.add(id);
	}
	public void removeGroup(Long id){
		groups.remove(id);
	}
	public Long[] getGroupIds(){
		return (Long[]) groups.toArray();
	}
}
