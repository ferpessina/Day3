import java.util.*;

public class SubscriptionManager {
	private List<Subscription> subs = new ArrayList<>();
	private Long subIds = new Long(0);
	private Set<String> subbedTags = new HashSet<>();
	
	public Long createSub(String tag){
		subs.add(new Subscription(tag, subIds));
		subbedTags.add(tag);
		subIds++;
		return subIds-1;
	}
	
	public void deleteSub(String tag){
		for(int i=0; i<subs.size(); i++){
			if(tag.equals(subs.get(i).getTag())){
				subs.remove(i);
				subbedTags.remove(tag);
			}
		}
	}
	
	public void deleteSub(Long id){
		for(int i=0; i<subs.size(); i++){
			if(id == subs.get(i).getId()){
				String tag = subs.get(i).getTag();
				subbedTags.remove(tag);
				subs.remove(i);
			}
		}
	}
	
	public Subscription getSub(Long id){
		Subscription ret = null;
		for(int i=0; i<subs.size(); i++){
			if(id == subs.get(i).getId()){
				ret = subs.get(i);
			}
		}
		return ret;
	}
	
	public Subscription getSub(String tag){
		Subscription ret = null;
		for(int i=0; i<subs.size(); i++){
			if(tag.equals(subs.get(i).getTag())){
				ret = subs.get(i);
			}
		}		
		return ret;
	}
	
	public String[] getSubsList(){
		return (String[])subbedTags.toArray();
	}
	
	public void listSubs(){
		System.out.println(subbedTags.toString());
	}
}
