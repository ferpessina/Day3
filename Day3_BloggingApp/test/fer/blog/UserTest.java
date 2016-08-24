package fer.blog;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	Long id = (long) 1;
	
	@Test(expected = IllegalArgumentException.class)
	public void testUserForEmptyName() {
		new User("","user@email.com",id);
	}
	@Test(expected = NullPointerException.class)
	public void testUserForNullName() {
		new User(null,"user@email.com",id);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testUserForEmptyEmail() {
		new User("UserName","",id);
	}
	@Test(expected = NullPointerException.class)
	public void testUserForNullEmail() {
		new User("UserName",null,id);
	}

//	@Test
//	public void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetId() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testSetName() {
		User u = new User("oldName","user@email.com",id);
		String expected = "New name";
		u.setName(expected);
		String actual = u.getName();
		assertEquals(expected, actual);		
	}

	@Test
	public void testSetEmail() {
		User u = new User("User name","old@email.com",id);
		String expected = "new@email.com";
		u.setEmail(expected);
		String actual = u.getEmail();
		assertEquals(expected, actual);		
	}

	@Test
	public void testPrintInfo() {
		User u = new User("User name","user@email.com",id);
		u.printInfo();
	}

}
