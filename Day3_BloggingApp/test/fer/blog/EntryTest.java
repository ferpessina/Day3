package fer.blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EntryTest {
	Entry e;
	String title = "the title";
	String text = "some text";
	String owner = "owner name";
	String tagString = "tag1 tag2 tag3";
	String tags = "[tag1, tag2, tag3]";
	Long id = (long) 1;
	
	@Test(expected = IllegalArgumentException.class)
	public void testEntryForEmptyTitle() {
		new Entry("",text,tagString,id,owner);
	}
	@Test(expected = NullPointerException.class)
	public void testEntryForNullTitle() {
		new Entry(null,text,tagString,id,owner);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEntryForEmptyOwner() {
		new Entry(title,text,tagString,id,"");
	}
	@Test(expected = NullPointerException.class)
	public void testEntryForNullOwner() {
		new Entry(title,text,tagString,id,null);
	}

	@Test
	public void testAddTag() {
		e = new Entry(title,text,"",id,owner);
		e.addTag("tag1");
		boolean expected = true;
		boolean actual = e.hasTag("tag1");
		assertEquals(expected, actual);
		e.addTag("tag2 tag3");
		actual = e.hasTag("tag2");
		assertEquals(expected, actual);
		actual = e.hasTag("tag3");
		assertEquals(expected, actual);
		e.addTag(null);//should do nothing
	}

	@Test
	public void testHasTagUsingCorrectInput() {
		e = new Entry(title,text,tagString,id,owner);
		boolean expected = true;
		boolean actual = e.hasTag("tag1");
		assertEquals(expected, actual);
		expected = false;
		actual = e.hasTag("blabla");
		assertEquals(expected, actual);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testHasTagUsingIncorrectInput() {
		e = new Entry(title,text,tagString,id,owner);
		e.hasTag("sometag anothertag");

	}
	@Test()
	public void testHasTagUsingNullInput() {
		e = new Entry(title,text,tagString,id,owner);
		boolean expected = false;
		boolean actual = e.hasTag(null);
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveTag() {
		e = new Entry(title,text,"tag1 tag2 tag3",id,owner);
		e.removeTag("tag1");
		boolean expected = false;
		boolean actual = e.hasTag("tag1");
		assertEquals(expected, actual);
		e.removeTag("tag2 tag3");
		actual = e.hasTag("tag2");
		assertEquals(expected, actual);
		actual = e.hasTag("tag3");
		assertEquals(expected, actual);
		e.removeTag(null);//should do nothing
	}

	@Test
	public void testGetTags() {
		e = new Entry(title,text,"tag1 tag2 tag3",id,owner);
		List<String> actual = e.getTags();
		List<String> expected = new ArrayList<>();
		expected.add("tag1");
		expected.add("tag2");
		expected.add("tag3");
		assertEquals(actual, expected);
	}

	@Test
	public void testGetTagsArray() {
		e = new Entry(title,text,"tag1 tag2 tag3",id,owner);
		String actual[] = e.getTagsArray();
		String expected[] = {"tag1","tag2","tag3"};
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testClearAllTags() {
		e = new Entry(title,text,"tag1 tag2 tag3",id,owner);
		e.clearAllTags();
		List<String> actual = e.getTags();
		List<String> expected = new ArrayList<>();
		assertEquals(actual, expected);
	}

	@Test
	public void testPrint() {
		e = new Entry(title,text,tagString,id,owner);
		e.print();
	}
	
	@Test
	public void testToStringArray(){
		e = new Entry(title,text,tagString,id,owner);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = e.getDate();
		String expected[] = {title, text, dateFormat.format(date), tagString,owner};
		String actual[] = e.toStringArray();
		System.out.println(Arrays.deepToString(actual));
		System.out.println(Arrays.deepToString(expected));
		assertArrayEquals(expected, actual);
	}


}
