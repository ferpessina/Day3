package fer.blog;

import java.util.*;

public class SubManager{
	private static Map <String, Set<User>> tagSubs = new HashMap<>();
	private static Map <String, Set<User>> userSubs = new HashMap<>();
	private static Map <String, Set<User>> groupSubs = new HashMap<>();
	
	public void createSub(String var, User subscriber, String type){
		Set<User> set;
		switch(type){
		case "tag":
			if(tagSubs.keySet().contains(var)){
				set = tagSubs.get(var);
				set.add(subscriber);
				tagSubs.put(var, set);
			}else{
				set = new HashSet<>();
				set.add(subscriber);
				tagSubs.put(var, set);
			}
			break;
		case "user":
			if(userSubs.keySet().contains(var)){
				set = userSubs.get(var);
				set.add(subscriber);
				userSubs.put(var, set);
			}else{
				set = new HashSet<>();
				set.add(subscriber);
				userSubs.put(var, set);
			}
			break;
		case "group":
			if(groupSubs.keySet().contains(var)){
				set = groupSubs.get(var);
				set.add(subscriber);
				groupSubs.put(var, set);
			}else{
				set = new HashSet<>();
				set.add(subscriber);
				groupSubs.put(var, set);
			}
			break;
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
	
	public void deleteUserSubs(User user){
		tagSubs.forEach((k,v)->v.remove(user));
		userSubs.forEach((k,v)->v.remove(user));
		groupSubs.forEach((k,v)->v.remove(user));
	}
	
	public void manageNewEntry(Entry _entry, GroupManager _gManager, UserManager _uManager){
		if(_entry != null){
			Thread t = new Thread(new ManagerThread(_entry,_gManager,_uManager));
			t.start();
		}
	}
	
	private static class ManagerThread implements Runnable{
		private static Entry entry;
		private GroupManager gManager;
		private UserManager uManager;
		
		public ManagerThread(Entry e, GroupManager g, UserManager u){
			entry = e;
			this.gManager = g;
			this.uManager = u;
		}
		
		public void run() {
			//check user for subscribers
			String userName = entry.getOwner();
			Set<User> subscribers = userSubs.get(userName);
			if(subscribers != null){
				subscribers.stream().forEach(ManagerThread::sendMail);
			}
			//check tags for subscribers
			List<String> tags = entry.getTags();
			subscribers = null;
			for(int i=0;i<tags.size();i++){
				subscribers = tagSubs.get(tags.get(i));
				if(subscribers != null){
					subscribers.stream().forEach(ManagerThread::sendMail);
				}
			}
			//check user groups for subscribers
			User user = uManager.getUser(userName);
			Set<String> userNames = gManager.getMembers(user.getGroups());
			if(userNames != null && userNames.size() >= 1){
				userNames.stream().forEach(u -> sendMail(uManager.getUser(u)));
			}
	    }
		
		private static void sendMail(User u) {
			System.out.println("Mail sent.");
			new MailService().entrySendByMail(u.getEmail(), entry);
		}
	}
	
}

