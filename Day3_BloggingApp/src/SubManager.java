import java.util.*;

public class SubManager implements Runnable{
	private Map <String, Set<User>> tagSubs = new HashMap<>();
	private Map <String, Set<User>> userSubs = new HashMap<>();
	private Map <String, Set<User>> groupSubs = new HashMap<>();

	private Entry entry;
	private GroupManager gManager;
	private UserManager uManager;
	
	public void createSub(String tag, User subscriber){
		Set<User> set;
		if(tagSubs.keySet().contains(tag)){
			set = tagSubs.get(tag);
			set.add(subscriber);
			tagSubs.put(tag, set);
		}else{
			set = new HashSet<>();
			set.add(subscriber);
			tagSubs.put(tag, set);
		}
	}
	public void createSub(User user, User subscriber){
		Set<User> set;
		String userName = user.getName();
		if(userSubs.keySet().contains(userName)){
			set = userSubs.get(userName);
			set.add(subscriber);
			userSubs.put(userName, set);
		}else{
			set = new HashSet<>();
			set.add(subscriber);
			userSubs.put(userName, set);
		}
	}
	public void createSub(Group group, User subscriber){
		String groupName = group.getName();
		Set<User> set;
		if(groupSubs.keySet().contains(groupName)){
			set = groupSubs.get(groupName);
			set.add(subscriber);
			groupSubs.put(groupName, set);
		}else{
			set = new HashSet<>();
			set.add(subscriber);
			groupSubs.put(groupName, set);
		}
	}
	
	
	public void deleteSub(String tag, User subscriber){
		Set<User> set;
		if(tagSubs.keySet().contains(tag)){
			set = tagSubs.get(tag);
			set.remove(subscriber);
			tagSubs.put(tag, set);
		}
	}
	public void deleteSub(User user, User subscriber){
		String userName = user.getName();
		Set<User> set;
		if(userSubs.keySet().contains(userName)){
			set = userSubs.get(userName);
			set.remove(subscriber);
			userSubs.put(userName, set);
		}
	}
	public void deleteSub(Group group, User subscriber){
		String groupName = group.getName();
		Set<User> set;
		if(groupSubs.keySet().contains(groupName)){
			set = groupSubs.get(groupName);
			set.remove(subscriber);
			groupSubs.put(groupName, set);
		}
	}
	
	public void manageNewEntry(Entry _entry, GroupManager _gManager, UserManager _uManager){
		entry = _entry;
		gManager = _gManager;
		uManager = _uManager;
		(new Thread(new SubManager())).start();

	}
	
	public void run() {
		//check user for subscribers
		String user = entry.getOwner();
		Set<User> subscribers = userSubs.get(user);
		subscribers.stream().forEach(SubManager::sendMail);
		//check tags for subscribers
		List<String> tags = entry.getTags();
		for(int i=0;i<tags.size();i++){
			subscribers = tagSubs.get(tags.get(i));
			subscribers.stream().forEach(SubManager::sendMail);
		}
		//check user groups for subscribers
		Set<String> userNames = gManager.getMembers(user);
		userNames.stream().forEach(u -> sendMail(uManager.getUser(u)));
		
    }
	private static void sendMail(User u) {
		System.out.println("Mail sent.");
		//
	}
	

	
}
