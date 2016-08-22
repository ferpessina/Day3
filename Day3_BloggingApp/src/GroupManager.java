import java.util.ArrayList;
import java.util.List;

public class GroupManager{
	private List<Group> groups = new ArrayList<>();
	private Long groupId = new Long(0);

	public Long newGroup(String groupName){
		groups.add(new Group(groupName, groupId));
		groupId++;
		return groupId-1;
	}
	
	public void removeGroup(String name){
		for(int i=0;i<groups.size();i++){
			if(name.equals(groups.get(i).getName())){
				groups.remove(i);
			}
		}
	}
	
	public List<String> getGroupNames(){
		List<String> ret = new ArrayList<>();
		for(int i=0; i<groups.size();i++){
			ret.add(groups.get(i).getName());
		}
		return ret;
	}
	
	public Group getGroup(String name){
		Group ret = null;
		for(int i=0;i<groups.size();i++){
			if(name.equals(groups.get(i).getName())){
				ret = groups.get(i);
			}
		}
		return ret;
	}
}