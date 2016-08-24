package fer.blog;
import java.util.*;

public class UserManager{
	private Map<User, List<Entry>> users = new HashMap<>();
	private Long userId = new Long(0);
	
	public void newUser(String userName, String userEmail){
		users.put(new User(userName, userEmail, userId),null);
		userId++;
	}
	
	public void deleteUser(String userName){
		User user = getUser(userName);
		if(user!=null)
			users.remove(user);
	}
	
	public void deleteUser(User user){
		if(user!=null)
			users.remove(user);
	}
	
	public int addUserEntry(String userName, Entry entry){
		User user = getUser(userName);
		if(user!=null){
			List<Entry> entries = users.get(user);
			if(entries!=null){
				entries.add(entry);
			}else{
				entries = new ArrayList<Entry>();
				entries.add(entry);
			}
			users.put(user, entries);
			return 0; //user found and entry added
		}else{
			return -1; // user not found
		}
	}
	public int addUserEntry(User user, Entry entry){
		if(user!=null){
			List<Entry> entries = users.get(user);
			if(entries!=null){
				entries.add(entry);
			}else{
				entries = new ArrayList<Entry>();
				entries.add(entry);
			}
			users.put(user, entries);
			return 0; //user found and entry added
		}else{
			return -1; // user not found
		}
	}
	
	public int deleteUserEntry(User user, Entry entry){
		if(user!=null){
			List<Entry> entries = users.get(user);
			entries.remove(entry);
			users.put(user, entries);
			return 0; //user found and entry added
		}else{
			return -1; // user not found
		}
	}
	
	public List<Entry> getUserPosts(String userName){
		User user = getUser(userName);
		if(user!=null){
			return users.get(user);
		}else{
			return null;
		}
	}
	
	public void printUserPosts(String userName){
		User user = getUser(userName);
		if(user != null){
			List<Entry> posts = users.get(user);
			if(posts!=null)
				posts.stream().forEach(e -> e.print());
			else
				System.out.println("No posts found for that user.");
		}else
			System.out.println("User not found.");
	}
	
	public User getUser(String userName){
		User ret = null;
		Set<User> _users = users.keySet();
		Iterator<User> iterator = _users.iterator(); 
		while (iterator.hasNext()){
			ret = iterator.next();
			if(ret.getName().equals(userName)){
				return ret;
			}else{
				ret = null;
			}
		}
		return ret;
	}
//	public List<String> getUserNames(){
//		List<String> ret = new ArrayList<>();
//		for(int i=0; i<users.size();i++){
//			ret.add(users.get(i).getName());
//		}
//		return ret;
//	}
//	
	public void userInfo(String userName){
		Set<User> _users = users.keySet();
		_users.stream().filter(e -> e.getName().equals(userName)).forEach(User::printInfo);;

	}
	public void printUserNames(){
		Set<User> _users = users.keySet();
		_users.stream().forEach(e -> System.out.println(e.getName()));

	}
//	
//	public Long getUserId(String userName){
//		Long ret = null;
//		for(int i=0;i<users.size();i++){
//			if(userName.equals(users.get(i).getName())){
//				ret = users.get(i).getId();
//				return ret;
//			}
//		}
//		return ret;
//	}
//	
//	public User getUser(String userName){
//		User ret = null;
//		for(int i=0;i<users.size();i++){
//			if(userName.equals(users.get(i).getName())){
//				ret = users.get(i);
//			}
//		}
//		return ret;
//	}
}