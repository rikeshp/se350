import java.util.Collection;

import junit.framework.TestCase;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
	
	//Creates objects for testing
	InventorySet set = new InventorySet();
	final VideoObj vid1 = new VideoObj("Inception", 2010, "Christopher Nolan");
	final VideoObj vid2 = new VideoObj("The Godfather", 1972, "Francis Ford Coppola");
	
	public InventoryTEST(String name) {
		super(name);
	}

	public void testSize() {
		set.clear(); //Reset set
		assertEquals(0, set.size()); //Nothing added, should be 0
		
		set.addNumOwned(vid1, 1);
		assertEquals(1, set.size()); //Only 1 record
		
		set.addNumOwned(vid1, 15);
		assertEquals(1, set.size()); //Still 1 record
		
		set.addNumOwned(vid2, 300);
		assertEquals(2, set.size()); //2 records exist
		
		set.addNumOwned(vid2, -301);
		assertEquals(1, set.size()); //1 record exists because vid2 should be removed
		
		set.addNumOwned(vid1, -300);
		assertEquals(0, set.size()); //0 records exist because vid 1 should be removed
		
		try { //Bad params
			set.addNumOwned(null, -1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try {//No video but bad change i.e. no record is created
			set.addNumOwned(vid1, 0);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}

	public void testAddNumOwned() {
		set.clear(); //Reset set
		assertEquals(0, set.size()); //Empty hashmap
		
		set.addNumOwned(vid1, 1);
		assertEquals(1, set.get(vid1).numOwned); //Only 1 video added to vid1
		
		set.addNumOwned(vid1, 500); //Adds 500
		assertEquals(501, set.get(vid1).numOwned);
		
		set.addNumOwned(vid1, -300); //Remove 300
		assertEquals(201, set.get(vid1).numOwned);
		
		set.addNumOwned(vid2, 5);
		assertEquals(5, set.get(vid2).numOwned); //Add 5 to vid 2
		
		assertEquals(2, set.size()); //Should only be 2 videos in Inventory
		
		try { //Bad params
			set.addNumOwned(null, -1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try {//No video but bad change i.e. no record is created
			set.addNumOwned(vid1, 0);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		
	}

	public void testCheckOutCheckIn() {
		set.clear();
		
		try { //No videos in set
			set.checkIn(null);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try { //No videos in set
			set.checkOut(null);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		set.addNumOwned(vid1, 1);
		assertTrue(set.get(vid1).numOut == 0 && set.get(vid1).numRentals == 0); //New record therefore none out nor rental
		
		try { //Can't check in a video that's not out
			set.checkIn(vid1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		set.checkOut(vid1);
		assertTrue(set.get(vid1).numOut == 1 && set.get(vid1).numRentals == 1); //1 video checked out therefore all should increment
		
		set.checkIn(vid1);
		assertTrue(set.get(vid1).numOut == 0 && set.get(vid1).numRentals == 1); //Checked in therefore 0 are out and rentals EVER is still 1
		
		set.checkOut(vid1); //Should now contain no available videos in inventory
		try { //Can't checkout a video when you don't have enough...or any!
			set.checkOut(vid1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}

	public void testClear() {
		set.clear();
		assertEquals(0, set.size()); //Reset set
		
		//Add videos
		set.addNumOwned(vid1, 2);
		set.addNumOwned(vid2, 30);
		
		assertEquals(2, set.size()); //Should be 2 videos
		
		set.clear();
		assertEquals(0, set.size()); //Cleared therefore 0 videos
		
		try { //No videos should exist
			set.checkOut(vid1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}

	public void testGet() {
		//Because a copy is supposedly returned then a new record is created each time
		//Therefore addresses should be different.
		//Since a .equals() was never created for the Record class, the default .equals() would just do a ==

		set.clear(); //No keys/values in set
		assertNull(set.get(vid1));
		
		set.addNumOwned(vid1, 1);
		assertNotNull(set.get(vid1));
		
		Record record1 = set.get(vid1);
		Record record2 = set.get(vid1);
		assertFalse(record1.equals(record2));
		assertTrue(record1 != record2);
	}

	public void testToCollection() {
		// Be sure to test that changing records in the returned
		// collection does not change the original records in the
		// inventory. ToCollection should return a COPY of the records,
		// not the records themselves.
		
		set.clear();
		
		assertTrue(set.toCollection() != set.toCollection()); //Addresses should be different if they were copies
		
		set.addNumOwned(vid1, 1);
		Collection<Record> recordAL = set.toCollection(); //Both should contain 1 of vid1
		
		set.addNumOwned(vid1, 15); //Inventory contains 16 of vid 1, but the copy should still contain 1
		assertTrue(set.get(vid1).numOwned != recordAL.iterator().next().numOwned); //Verify if above is true
		
		set.addNumOwned(vid2, 2); //Adding another record to the inventory. The copy should still be just 1 record
		assertEquals(2, set.size());
		assertEquals(1, recordAL.size());
		
		set.clear(); //Nothing should be in inventory
		assertEquals(0, set.size());
		assertEquals(1, recordAL.size()); //But the copy should still contain 1 record
	}

}
