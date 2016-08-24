
public class Group {
	private Long id;
	private String name;

	Group(String _name, Long _id){
		this.name = _name;
		this.id = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String _name){
		name = _name;
	}
}
