import java.util.*;
import java.util.stream.Collectors;

public class GroupManager{
	private Map<Group,Set<String>> groups = new HashMap<>();
	private Long groupId = new Long(0);

	public Long newGroup(String groupName){
		groups.put(new Group(groupName, groupId),null);
		groupId++;
		return groupId-1;
	}
	
	public void deleteGroup(String name){
		Group group = getGroupByName(name);
		groups.remove(group);
	}

	public int addGroupUser(Group group, User user){
		String userName = user.getName();
		if(group!=null){
			Set<String> users = groups.get(group);
			if(users!=null){
				users.add(userName);
			}else{
				users = new HashSet<String>();
				users.add(userName);
			}
			groups.put(group, users);
			return 0; //group found and user added
		}else{
			return -1; //group not found
		}
	}
	public void removeUserFromAllGroups(User user){
		String userName = user.getName();
		groups.forEach( (k,v) -> v.remove(userName));
	}
	
	public int removeGroupUser(String groupName, User user){
		String userName = user.getName();
		Group group = getGroupByName(groupName);
		if(group!=null){
			Set<String> users = groups.get(group);
			if(users!=null){
				users.remove(userName);
			}
			return 0; // user removed
		}
		return -1; //group not found
	}
	
	public void listGroups(){
		groups.keySet().stream().forEach(g -> System.out.println(g.getName()));
	}
	
	public void printGroup(String groupName){
		Group group = getGroupByName(groupName);
		if(group != null){
			System.out.println("Group '"+ groupName +"' members:");
			groups.get(group).stream().forEach(u -> System.out.println(u));
		}else{
			System.out.println("Group not found.");;
		}
	}
	
	public Set<String> getGroupMembers(String groupName){
		Set<String> members = null;
		Group group = getGroupByName(groupName);
		if(group != null){
			members = groups.get(group);
			return members;
		}else{
			System.out.println("Group not found.");;
		}
		return members;
	}

	public Group getGroupByName(String groupName){
		Group ret = null;
		Set<Group> _groups = groups.keySet();
		Iterator<Group> iterator = _groups.iterator(); 
		while (iterator.hasNext()){
			ret = iterator.next();
			if(ret.getName().equals(groupName)){
				return ret;
			}else{
				ret = null;
			}
		}
		return ret;
	}
	
	public Set<String> getMembers(String member){
		Set<Group> _groups = groups.keySet().stream().filter(g -> groups.get(g).contains(member)).collect(Collectors.toCollection(HashSet::new));
		Set<String> members = new HashSet<>();
		Iterator<Group> iterator = _groups.iterator(); 
		while (iterator.hasNext()){
			Group aux = iterator.next();
			if(groups.containsKey(aux)){
				members.addAll(groups.get(aux));
			}
		}
		return members;
	}
}