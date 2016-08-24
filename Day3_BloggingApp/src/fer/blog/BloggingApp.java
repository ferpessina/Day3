package fer.blog;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BloggingApp {
	static Scanner in;
	static TagManager tManager = new TagManager();
	static UserManager uManager = new UserManager();
	static GroupManager gManager = new GroupManager();
	static EntryManager eManager = new EntryManager();
	static SubManager sManager = new SubManager();
	static User loggedUser = null;

	public static void main(String[] args) throws ParseException {
		in = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("Welcome to my blogging app!");
		String command = "";
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
				System.out.println("uinfo -> See user info");
				System.out.println("user posts -> List all posts from user");
				System.out.println("group posts -> List all posts from group");
				System.out.println("groups -> List groups");
				System.out.println("logout -> Log out");
				System.out.println("exit -> Exit app");
				break;
			case "new":
				createEntry();
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
				eManager.getEntriesByTag(_aux).stream().forEach(Entry::print);
				break;
			case "uinfo":
				System.out.println("Enter user name");
				_aux = in.nextLine();
				uManager.userInfo(_aux);
				break;
			case "user posts":
				System.out.println("Enter user you wish to search by...");
				_aux = in.nextLine();
				uManager.printUserPosts(_aux);
				break;
			case "pshow":
				System.out.println("Enter the title of the post you wish to see...");
				_aux = in.nextLine();
				eManager.printEntry(_aux);
				break;
			case "sdate":
				System.out.println("Sorting by date");
				eManager.sortAllByDate(true);
				break;
			case "stitle":
				System.out.println("Sorting by title");
				eManager.sortAllByTitle(true);
				break;
			case "sowner":
				System.out.println("Sorting by owner");
				eManager.sortAllByOwner(true);
				break;
			case "pdelete":
				deleteEntry();
				break;
			case "subscribe":
				newSub();
				break;
			case "gcreate":
				createGroup(loggedUserName);
				break;
			case "ginfo":
				System.out.println("Enter group name:");
				_aux = in.nextLine();
				gManager.printGroup(_aux);
				break;
			case "recent":
				System.out.println("Enter the number of recent posts you wish to see...");
				int n = in.nextInt();
				in.nextLine();
				eManager.printLastEntries(n);
				break;
			case "logout":
				loggedUser = null;
				break;
			case "udelete":
				deleteUser();
				break;
			case "groups":
				gManager.listGroups();
				break;
			case "sbydate":
				System.out.println("Enter the dates and times from which you want to search:");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				System.out.println("Date and time format: dd/MM/yyyy HH:mm:ss.");
				Date from = null;
				while(from == null){
					System.out.print("Search from: ");
					from = dateFormat.parse(in.nextLine());
					System.out.println();
				}
				Date to = null;
				while(to == null){
					System.out.print(" to: ");
					to = dateFormat.parse(in.nextLine());
					System.out.println();
				}
				eManager.getEntriesByDate(from, to).stream().forEach(Entry::print);
				break;
			case "group posts":
				System.out.println("Enter group name:");
				_aux = in.nextLine();
				Set<String> members = gManager.getGroupMembers(_aux);
				eManager.getEntriesByUsers(members).stream().forEach(Entry::print);
				break;
			case "exit":
				System.out.println("Exiting blog...");				
				break;
			}
		}
		in.close();
	}

	static private void createEntry(){
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
		String tags = in.nextLine();
		Entry entry = eManager.addEntry(title, text, tags, loggedUser.getName());
		uManager.addUserEntry(loggedUser, entry);
		String[] split = tags.split("\\s+");
		tManager.addTags(split);
		sManager.manageNewEntry(entry, gManager, uManager);
	}
	
	static private void createGroup(String userName){
		System.out.println("Enter new group name:");
		String groupName = in.nextLine();
		Group group = gManager.getGroupByName(groupName);
		if(group == null){
			gManager.newGroup(groupName);
			group = gManager.getGroupByName(groupName);
			System.out.println("Group created!\n Do you wish to join the group '"+groupName+"'? (y/n)");
			String ans = in.nextLine();
			if(ans.equals("y")){
				gManager.addGroupUser(group, loggedUser);
			}else{
				return;
			}
		}else{
			System.out.println("Group already exists.\n Do you wish to join the group '"+groupName+"'? (y/n)");
			String ans = in.nextLine();
			if(ans.equals("y")){
				gManager.addGroupUser(group, loggedUser);
			}else{
				return;
			}
		}

	}
	static private void deleteUser(){
		System.out.println("Enter the name of the user you wish to delete...");
		String name = in.nextLine();
		User temp = uManager.getUser(name);
		if(temp == loggedUser){
			uManager.deleteUser(temp);
			gManager.removeUserFromAllGroups(temp);
			sManager.deleteUserSubs(temp);
			loggedUser = null;
		}else{
			uManager.deleteUser(temp);
			gManager.removeUserFromAllGroups(temp);
			sManager.deleteUserSubs(temp);

		}
	}
	
	static private void deleteEntry(){
		System.out.println("Enter the title of the post you wish to delete...");
		String title = in.nextLine();
		Entry temp = eManager.getEntry(title);
		tManager.removeTags(temp.getTagsArray());
		uManager.deleteUserEntry(uManager.getUser(temp.getOwner()), temp);
		eManager.deleteEntry(temp);
	}
	
	static private void newSub(){
		System.out.println("Enter the type of subscription:");
		String type = in.nextLine();
		String var;
		switch(type){
		case "tag":
			System.out.println("Enter tag:");
			var = in.nextLine();
			sManager.createSub(var, loggedUser, type);
			break;
		case "user":
			System.out.println("Enter user:");
			var = in.nextLine();
			sManager.createSub(var, loggedUser, type);
			break;
		case "group":
			System.out.println("Enter group:");
			var = in.nextLine();
			sManager.createSub(var, loggedUser, type);
			break;
		}
	}
}

