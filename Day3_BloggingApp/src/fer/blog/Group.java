package fer.blog;

public class Group {
	private Long id;
	private String name;

	Group(String _name, Long _id){
		if(_name.equals(""))
			throw new IllegalArgumentException("Group name can't be empty");
		name = _name;
		this.id = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String _name){
		if(_name.equals(""))
			throw new IllegalArgumentException("Group name can't be empty");
		name = _name;
	}
}
