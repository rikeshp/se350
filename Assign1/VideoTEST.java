import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
	public VideoTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		String title1 = "XX";
		String director1 = "XY";
		String title2 = " XX ";
		String director2 = " XY ";
		int year = 2002;

		VideoObj v1 = new VideoObj(title1, year, director1);
		assertSame(title1, v1.title());
		assertEquals(year, v1.year());
		assertSame(director1, v1.director());

		VideoObj v2 = new VideoObj(title2, year, director2);
		assertEquals(title1, v2.title());
		assertEquals(director1, v2.director());
	}

	public void testConstructorExceptionYear() {
		try {
			new VideoObj("X", 1800, "Y");
			fail();
		} catch (IllegalArgumentException e) {}
		
		try {
			new VideoObj("X", 5000, "Y");
			fail();
		} catch (IllegalArgumentException e) {}
		
		try {
			new VideoObj("X", 1801, "Y");
			new VideoObj("X", 4999, "Y");
		} catch (IllegalArgumentException e) {fail();}
	}

	public void testConstructorExceptionTitle() {
		try {
			new VideoObj(null, 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) {}
		try {
			new VideoObj("", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) {}
		try {
			new VideoObj(" ", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) {}
	}

	public void testConstructorExceptionDirector() {
		// TODO
	}

	public void testHashCode() {
		assertEquals(-875826552, new VideoObj("None", 2009, "Zebra").hashCode());
		assertEquals(-1391078111, new VideoObj("Blah", 1954, "Cante").hashCode());
	}

	public void testEquals() {
		VideoObj vid1 = new VideoObj("None", 2009, "Zebra");
		VideoObj vid2 = new VideoObj("None", 2009, "Zebra");
		VideoObj vid3 = new VideoObj("None", 2009, "Zebra");
		
		assertTrue(vid1.equals(vid1)); //Reflexivity: must equal itself
		assertTrue(vid1.equals(new VideoObj("None", 2009, "Zebra"))); //Symmetry: must equal a new object of itself
		assertTrue(vid1.equals(vid2) && vid2.equals(vid3) && vid1.equals(vid3)); //Transitivity: x = y, y = z, therefore x = z
		
		assertFalse(vid1.equals(new VideoObj("None", 2010, "Zebra"))); //Bad year
		assertFalse(vid1.equals(new VideoObj("Something", 2009, "Zebra"))); //Bad title
		assertFalse(vid1.equals(new VideoObj("None", 2009, "Elephant"))); //Bad director
		assertFalse(vid1.equals(new VideoObj("Something", 2010, "Elephant"))); //Bad everything
		assertFalse(vid1.equals(new Object())); //Not an instanceof videoObj
		assertFalse(vid1.equals(null)); //Bad video
	}

	public void testCompareTo() {
		String title1 = "A", title2 = "B";
		int year1 = 2000, year2 = 2010;
		String dir1 = "Cante", dir2 = "Marcus";
		
		VideoObj vid1 = new VideoObj(title1, year1, dir1);
		VideoObj vid2 = new VideoObj(title2, year2, dir2);
		
		assertTrue(vid1.compareTo(vid2) < 0); //All fields should be less than
		assertTrue(vid1.compareTo(vid2) == -vid2.compareTo(vid1));
		assertTrue(vid1.compareTo(vid1) == 0); //Should equal itself
		
		try {
			vid1.compareTo(new Object());
			fail("Exception is thrown");
		} catch(ClassCastException e) {}
		
		try {
			vid1.compareTo(null);
			fail("Exception is thrown");
		} catch(NullPointerException e) {}
		catch (ClassCastException e) {}
		
		
	}
 
	public void testToString() {
		VideoObj vid1 = new VideoObj("None", 2009, "Zebra");
		VideoObj vid2 = new VideoObj("Blah", 1954, "Cante");
		
		String answer = "None (2009) : Zebra";
		assertTrue(vid1.toString().equals(answer));
		assertFalse(vid2.toString().equals(answer));
		}
}
