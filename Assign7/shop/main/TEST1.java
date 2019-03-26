package shop.main;

import java.util.Comparator;
import java.util.Iterator;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
//done
public final class TEST1 extends TestCase {
	private Inventory _inventory = Data.newInventory();

	public TEST1(String name) {
		super(name);
	}

	private void expect(Video v, String s) {
		Assert.assertEquals(s, _inventory.get(v).toString());
	}

	private void expect(Record r, String s) {
		Assert.assertEquals(s, r.toString());
	}

	public void test1() {
		Command clearCmd = Data.newClearCmd(_inventory);
		clearCmd.run();

		Video v1 = Data.newVideo("Title1", 2000, "Director1");
		Assert.assertEquals(0, _inventory.size());
		Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
		Assert.assertEquals(1, _inventory.size());
		Assert.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
		Assert.assertEquals(1, _inventory.size());
		// System.out.println(_inventory.get(v1));
		expect(v1, "Title1 (2000) : Director1 [10,0,0]");

		// Adding new video
		Video v2 = Data.newVideo("Title2", 2010, "DIRECTOR2");
		assertEquals(1, _inventory.size());
		assertTrue(Data.newAddCmd(_inventory, v2, 100).run());
		assertEquals(2, _inventory.size());
		assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
		assertEquals(2, _inventory.size());
		expect(v2, "Title2 (2010) : DIRECTOR2 [101,0,0]");

		// Adding null
		assertFalse(Data.newAddCmd(_inventory, null, 100).run());
		assertEquals(2, _inventory.size());

		// Checking out
		assertTrue(Data.newOutCmd(_inventory, v2).run());
		expect(v2, "Title2 (2010) : DIRECTOR2 [101,1,1]");

		// Checking in
		assertTrue(Data.newInCmd(_inventory, v2).run());
		expect(v2, "Title2 (2010) : DIRECTOR2 [101,0,1]");

		assertEquals(2, _inventory.size());

		// Removing video with negative change, overall 0 entries
		assertTrue(Data.newAddCmd(_inventory, v2, -101).run());
		assertEquals(1, _inventory.size());

		// Mass checkout i.e. checkout all v1
		for (int x = 0; x < _inventory.get(v1).numOwned(); x++) {
			expect(v1, "Title1 (2000) : Director1 [10," + x + "," + x + "]");
			assertTrue(Data.newOutCmd(_inventory, v1).run());
		}
		expect(v1, "Title1 (2000) : Director1 [10,10,10]");

		// Mass checkin i.e. checkin all entries
		int currentNumOut = _inventory.get(v1).numOut();
		int rentals = _inventory.get(v1).numRentals();
		for (int x = 0; x < _inventory.get(v1).numOwned(); x++) {
			expect(v1, "Title1 (2000) : Director1 [10," + currentNumOut-- + "," + rentals + "]");
			assertTrue(Data.newInCmd(_inventory, v1).run());
		}
		expect(v1, "Title1 (2000) : Director1 [10,0,10]");
		assertTrue(Data.newAddCmd(_inventory, v2, 20).run());
		
		//iterator test
		Iterator<Record> iter = _inventory.iterator((r1, r2) -> r2.numOwned() - r1.numOwned());
		expect(iter.next(), "Title2 (2010) : DIRECTOR2 [20,0,0]");
		expect(iter.next(), "Title1 (2000) : Director1 [10,0,10]");
		assertFalse(iter.hasNext());
	}
}
