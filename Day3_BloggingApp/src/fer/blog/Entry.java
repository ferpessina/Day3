package fer.blog;
// Implements Blog entries
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Entry {
	private String ownerName;
	private String title;
	private String text;
	private Date date = new Date();
	private Set<String> tags = new HashSet<>();
	private Long entryId;
	
	Entry(String new_title,String new_text,String new_tags, Long id, String owner){
		if(new_title.equals(""))
			throw new IllegalArgumentException("Title can't be empty");
		title = new_title;
		if(new_text == null){
			text = "";
		}else{
			text = new_text;
		}
		date = new Date();
		if(new_tags != null){
			String[] _tags = new_tags.split("\\s+");
			Arrays.asList(_tags).stream().forEach(t -> {if(!t.equals("")){tags.add(t);}});
		}
		entryId = id;
		if(owner.equals(""))
			throw new IllegalArgumentException("Creator name can't be empty");
		ownerName = owner;
	}
	
	public void setTitle(String new_title){
		if(new_title.equals(""))
			throw new IllegalArgumentException("Title can't be empty");
		title = new_title;
	}
	
	public void setText(String new_text){
		text = new_text;
	}
	
	public void setDate(Date new_date){
		if(new_date == null)
			date = new Date();
		else
			date = new_date;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getText(){
		return text;
	}
	
	public Date getDate(){
		return date;
	}
	
	public Long getId(){
		return entryId;
	}
	
	public String getOwner(){
		return ownerName;
	}
	
	public void addTag(String tag){ //If spaces are present splits into multiple tags and adds.
		if(tag != null){
			String[] _tags = tag.split("\\s+");
			Arrays.asList(_tags).stream().forEach(t -> tags.add(t));
		}
	}
	
	public boolean hasTag(String tag){
		if(tag == null){
			return false;
		}
		if(!tag.equals("")){
	        for(int i = 0; i < tag.length(); i++){
	            if(Character.isWhitespace(tag.charAt(i))){
	                throw new IllegalArgumentException("Must not contain whitespaces");
	            }
	        }
	        boolean res = false;
			res = tags.contains(tag);
			return res;
	    }
		return false;
	}
	
	public void removeTag(String tag){
		if(tag != null){
			String[] _tags = tag.split("\\s+");
			Arrays.asList(_tags).stream().forEach(t -> tags.remove(t));
		}
	}
	
	public ArrayList<String> getTags(){
		ArrayList<String> list = new ArrayList<String>(tags);
		return list;
	}
	
	public String[] getTagsArray(){
		return tags.toArray(new String[tags.size()]);
	}
	
	public void clearAllTags(){
		tags.clear();
	}
	
	public void print(){
		System.out.println();
		System.out.println(title);
		System.out.println(text);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println("Created by \""+ownerName+"\" on: "+dateFormat.format(date));
		System.out.println("Tags: "+tags);
	}
	
	public String[] toStringArray(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		tags.stream().forEach(t -> builder.append(t+" "));
		String array[] = {title, text, dateFormat.format(date), builder.toString().trim() ,ownerName};
		
		return array;
	}
}
