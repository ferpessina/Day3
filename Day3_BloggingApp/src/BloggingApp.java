import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class BloggingApp {
	static Scanner in;
	static TagManager tManager = new TagManager();
	static UserManager uManager = new UserManager();
	static GroupManager gManager = new GroupManager();
	static EntryManager eManager = new EntryManager();
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("Welcome to my blogging app!");
		String command = "";
		User loggedUser = null;
		String loggedUserName = "";
		while(!command.equals("exit")){
			while(loggedUser == null){
				System.out.println("Enter your user name...");
				loggedUserName = in.nextLine();
				loggedUser = uManager.getUser(loggedUserName);
				if(loggedUser == null){
					System.out.println("User not found. Create new user \""+loggedUserName+"\"? (y/n)");
					String ans = in.nextLine();
					switch(ans){
					case "y":
						System.out.println("Enter your email:");
						String ownerEmail = in.nextLine();
						uManager.newUser(loggedUserName, ownerEmail);
						loggedUser = uManager.getUser(loggedUserName);
						break;
					case "n":
						break;
					}
				}
			}
			System.out.println();
			System.out.println("Enter a command...");
			command = in.nextLine();
			command = command.toLowerCase();
			String _aux;
			switch(command){
			case "help":
				System.out.println("new -> New entry");
				System.out.println("all tags -> Show all tags");
				System.out.println("tag -> Search by tag");
				System.out.println("pshow -> Show post");
				System.out.println("recent -> Recent entries");
				System.out.println("pdelete -> Delete entry");
				System.out.println("gcreate -> Create group");
				System.out.println("users -> List users");
				System.out.println("groups -> List groups");
				System.out.println("logout -> Log out");
				System.out.println("exit -> Exit app");
				break;
			case "new":
				createEntry(loggedUserName);
				break;
			case "all tags":
				tManager.printAllTags();
				break;
			case "users":
				uManager.printUserNames();
				break;
			case "tag":
				System.out.println("Enter tag you wish to search by...");
				_aux = in.nextLine();
				eManager.printEntriesByTag(_aux);
				break;
			case "pshow":
				System.out.println("Enter the title of the post you wish to see...");
				_aux = in.nextLine();
				eManager.printEntry(_aux);
				break;
			case "pdelete":
				deleteEntry();
				break;
			case "gcreate":
				createGroup(loggedUserName);
				break;
			case "recent":
				System.out.println("Enter the number of recent posts you wish to see...");
				int n = in.nextInt();
				in.nextLine();
				eManager.showLastEntries(n);
				break;
			case "logout":
				loggedUser = null;
				break;
			case "exit":
				System.out.println("Exiting blog...");				
				break;
			}
		}
		in.close();
	}

	static void createEntry(String ownerName){
		System.out.println();
		//TODO: Check for owner existence and update/create owner data
		System.out.println("Creating new entry...");		
		System.out.println("Enter title:");
		String title = in.nextLine();
		System.out.println("Enter text:");
		String text = in.nextLine();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Entry date: "+dateFormat.format(date)); //2014/08/06 15:59:48
		System.out.println("Add tags to your post:");
		String aux = in.nextLine();
		String[] split = aux.split("\\s+");
		Long entryId = eManager.addEntry(title, text, date, split, ownerName);
		User ownerUser = uManager.getUser(ownerName);
		ownerUser.addPost(entryId);
		tManager.addTags(split);
	}
	
	static void createGroup(String userName){
		System.out.println("Enter new group name:");
		String groupName = in.nextLine();
		Group group = gManager.getGroup(groupName);
		if(group == null){
			gManager.newGroup(groupName);
			group = gManager.getGroup(groupName);
			System.out.println("Group created!\n Do you wish to join the group '"+groupName+"'? (y/n)");
			String ans = in.nextLine();
			if(ans.equals("y")){
				group.addUser(uManager.getUserId(userName));
				uManager.getUser(userName).addGroup(group.getId());
			}else{
				return;
			}
		}else{
			System.out.println("Group already exists.\n Do you wish to join the group '"+groupName+"'? (y/n)");
			String ans = in.nextLine();
			if(ans.equals("y")){
				group.addUser(uManager.getUserId(userName));
				uManager.getUser(userName).addGroup(group.getId());
			}else{
				return;
			}
		}

	}
	
	static void deleteEntry(){
		System.out.println("Enter the title of the post you wish to delete...");
		String title = in.nextLine();
		Entry temp = eManager.getEntry(title);
		String entryOwner = temp.getOwner();
		Long entryId = temp.getId();
		tManager.removeTags(temp.getTagsArray());
		eManager.deleteEntry(entryId);
		User ownerUser = uManager.getUser(entryOwner);
		ownerUser.removePost(entryId);
	}
}

