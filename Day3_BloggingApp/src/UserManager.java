import java.util.ArrayList;
import java.util.List;

public class UserManager{
	private List<User> users = new ArrayList<>();
	private Long userId = new Long(0);
	
	public void newUser(String userName, String userEmail){
		users.add(new User(userName, userEmail, userId));
		userId++;
	}
	
	public void removeUser(String userName){
		for(int i=0;i<users.size();i++){
			if(userName.equals(users.get(i).getName())){
				users.remove(i);
			}
		}
	}
	
	public List<String> getUserNames(){
		List<String> ret = new ArrayList<>();
		for(int i=0; i<users.size();i++){
			ret.add(users.get(i).getName());
		}
		return ret;
	}
	
	public void printUserNames(){
		for(int i=0; i<users.size();i++){
			System.out.println(users.get(i).getName());
		}
	}
	
	public Long getUserId(String userName){
		Long ret = null;
		for(int i=0;i<users.size();i++){
			if(userName.equals(users.get(i).getName())){
				ret = users.get(i).getId();
				return ret;
			}
		}
		return ret;
	}
	
	public User getUser(String userName){
		User ret = null;
		for(int i=0;i<users.size();i++){
			if(userName.equals(users.get(i).getName())){
				ret = users.get(i);
			}
		}
		return ret;
	}
}