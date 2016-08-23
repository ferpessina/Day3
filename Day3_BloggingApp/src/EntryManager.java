import java.util.*;

public class EntryManager{
	private List<Entry> entries = new ArrayList<>();
	private Long entryId = new Long(0);

	public Entry addEntry(String title,String text,Date date,String[] tags, String owner){
		Entry ret = new Entry(title, text, date, tags, this.entryId, owner);
		entries.add(ret);
		entryId++;
		return ret;
	}
	
	public void deleteEntry(Entry entry){
		entries.remove(entry);
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
	
	public void printEntriesByTag(String tag){
		entries.stream().filter(p -> p.hasTag(tag)).forEach(Entry::print);
	}
	
	public void printEntriesByText(String text){
		entries.stream().filter(p -> p.getText().equals(text)).forEach(Entry::print);
	}
	
	public void printEntriesByDate(Date from, Date to){
		if(from != null && to != null){
			entries.stream().filter(p -> p.getDate().before(to) && p.getDate().after(from)).forEach(Entry::print);
		}
	}
	
	public void printEntriesByUsers(Set<String> users){
		entries.stream().filter(e -> users.contains(e.getOwner())).forEach(Entry::print);
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

	private void printEntry(int idx){
		if(idx<entries.size()){
			entries.get(idx).print();
		}
	}
}