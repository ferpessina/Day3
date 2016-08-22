import java.util.HashMap;
import java.util.Map;

public class TagManager{
	private Map<String,Integer> allTags = new HashMap<>();
	
	public void addTag(String tag){
		if(allTags.containsKey(tag)){
			int k = allTags.get(tag);
			allTags.put(tag, k+1);
		}else{
			allTags.put(tag, 1);
		}
	}
	
	public void addTags(String[] tags){
		for(int i=0;i<tags.length;i++){
			this.addTag(tags[i]);
		}
	}
	
	public void removeTag(String tag){
		if(allTags.containsKey(tag)){
			int k = allTags.get(tag);
			if(k<=1){
				allTags.remove(tag);
			}else{
				allTags.put(tag, k-1);
			}
		}
	}
	
	public void removeTags(String[] tags){
		for(int i=0;i<tags.length;i++){
			this.removeTag(tags[i]);
		}
	}

	public void printAllTags(){
		System.out.println("Tags: "+allTags.keySet());
	}

}