import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryManager{
	private List<Entry> entries = new ArrayList<>();
	private Long entryId = new Long(0);

	public Long addEntry(String title,String text,Date date,String[] tags, String owner){
		entries.add(new Entry(title, text, date, tags, this.entryId, owner));
		entryId++;
		return entryId-1;
	}
	
	public void printEntriesByTag(String tag){
		for(int i=0;i<entries.size();i++){
			if(entries.get(entries.size()-1-i).hasTag(tag)){
				this.printEntry(entries.size()-1-i);
			}
		}
	}
	
	public void printEntry(int idx){
		if(idx<entries.size()){
			entries.get(idx).print();;
		}
	}
	
	public void deleteEntry(Long id){
		for(int i=0;i<entries.size();i++){
			if(id == entries.get(i).getId()){
				entries.remove(i);
			}
		}
	}
	
	public Entry getEntry(String _title){
		Entry entry = null;
		for(int i=0;i<entries.size();i++){
			if(_title.equals(entries.get(i).getTitle())){
				return entries.get(i);
			}
		}
		return entry;
	}
	
	public Entry getEntry(int idx){
		Entry entry = null;
		if(idx<entries.size()){
			return entries.get(idx);
		}
		return entry;
	}
	
	public void printEntry(String _title){
		boolean not_found = true;
		for(int i=0;i<entries.size();i++){
			if(_title.equals(entries.get(i).getTitle())){
				entries.get(i).print();
				not_found = false;
			}
		}
		if(not_found){
			System.out.println("No posts found with that title.");
		}
	}
	
	public Entry getLastEntry(){
		return entries.get(entries.size()-1);
	}
	
	public void showLastEntries(int n){
		if(entries.size() == 0){
			System.out.println("No recent posts to show.");
		}
		if(n>entries.size()){
			n = entries.size();
		}
		for(int i=0;i<n;i++){
			this.printEntry(entries.size()-1-i);
		}
	}
}