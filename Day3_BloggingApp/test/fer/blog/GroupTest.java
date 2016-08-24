package fer.blog;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupTest {
	Long id = (long) 1;
	String name = "group name";
	
	@Test(expected = IllegalArgumentException.class)
	public void testGroupForEmptyName() {
		new Group("",id);
	}
	@Test(expected = NullPointerException.class)
	public void testGroupForNullName() {
		new Group(null,id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameWithEmptyName() {
		Group g = new Group(name,id);
		g.setName("");
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetNameWithNullName() {
		Group g = new Group(null,id);
		g.setName("");
	}

	@Test
	public void testGetName() {
		Group g = new Group(name,id);
		String expected = name;
		String actual = g.getName();
		assertEquals(expected, actual);
	}
}
