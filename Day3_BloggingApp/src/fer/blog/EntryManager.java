package fer.blog;
import java.util.*;
import java.util.stream.Collectors;

public class EntryManager{
	private List<Entry> entries = new ArrayList<>();
	private Long entryId = new Long(0);

	public Entry addEntry(String title,String text,String tags, String owner){
		Entry ret = new Entry(title, text, tags, this.entryId, owner);
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
	
	public void sortAllByDate(boolean oldFirst){
		Collections.sort(entries, new Comparator<Entry>() {
		    @Override
		    public int compare(Entry o1, Entry o2) {
		        return o1.getDate().compareTo(o2.getDate());
		    }
		});
		if(oldFirst){
			Collections.reverse(entries);
		}
	}
	
	public void sortAllByTitle(boolean ascending){
		Collections.sort(entries, new Comparator<Entry>() {
		    @Override
		    public int compare(Entry o1, Entry o2) {
		        return o1.getTitle().compareTo(o2.getTitle());
		    }
		});
		if(ascending){ // the print loops from back to front
			Collections.reverse(entries);
		}
	}
	
	public void sortAllByOwner(boolean ascending){
		Collections.sort(entries, new Comparator<Entry>() {
		    @Override
		    public int compare(Entry o1, Entry o2) {
		        return o1.getOwner().compareTo(o2.getOwner());
		    }
		});
		if(ascending){
			Collections.reverse(entries);
		}
	}
	
	public List<Entry> sortByDate(List<Entry> _entries){
		Collections.sort(_entries, new Comparator<Entry>() {
		    @Override
		    public int compare(Entry o1, Entry o2) {
		        return o1.getDate().compareTo(o2.getDate());
		    }
		});
		return _entries;
	}
	
	public List<Entry> getEntriesByTag(String tag){
		 return entries.stream().filter(p -> p.hasTag(tag)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<Entry> getEntriesByText(String text){
		return entries.stream().filter(p -> p.getText().equals(text)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<Entry> getEntriesByDate(Date from, Date to){
		if(from != null && to != null){
			return entries.stream().filter(p -> p.getDate().before(to) && p.getDate().after(from)).collect(Collectors.toCollection(ArrayList::new));
		}
		return null;
	}
	
	public List<Entry> getEntriesByUsers(Set<String> users){
		return entries.stream().filter(e -> users.contains(e.getOwner())).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public Entry getLastEntry(){
		return entries.get(entries.size()-1);
	}
	
	public void printLastEntries(int n){
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