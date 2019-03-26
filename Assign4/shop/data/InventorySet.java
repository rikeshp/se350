package shop.data;

import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import shop.command.Command;

/**
 * Implementation of Inventory interface.
 * 
 * @see Data
 */
final class InventorySet implements Inventory {
	// Chose to use Map of Record, rather than RecordObj, because of
	// Java's broken generic types. The story is too sad to retell, but
	// involves the fact that Iterable<? extends Record> is not a valid
	// type, and that Iterator<RecordObj> is not a subtype of
	// Iterator<Record>.
	//
	// Seems like the best approach for Java generics is to use the
	// external representation internally and downcast when necessary.
	private final Map<Video, Record> _data;

	InventorySet() {
		_data = new HashMap<Video, Record>();
	}

	public int size() {
		return _data.size();
  }

	public Record get(Video v) {
		return _data.get(v);
	}

	public Iterator<Record> iterator() {
		return Collections.unmodifiableCollection(_data.values()).iterator();
	}

	public Iterator<Record> iterator(Comparator<Record> comparator) {
		ArrayList<Record> list = new ArrayList<Record>(_data.values());
		Collections.sort(list, comparator);
		
		return Collections.unmodifiableList(list).iterator();
	}

	/**
	 * Add or remove copies of a video from the inventory. If a video record is not
	 * already present (and change is positive), a record is created. If a record is
	 * already present, <code>numOwned</code> is modified using <code>change</code>.
	 * If <code>change</code> brings the number of copies to be less than one, the
	 * record is removed from the inventory.
	 * 
	 * @param video
	 *            the video to be added.
	 * @param change
	 *            the number of copies to add (or remove if negative).
	 * @throws IllegalArgumentException
	 *             if video null or change is zero
	 */
	void addNumOwned(Video video, int change) {	
		if(video == null || change == 0) //Bad params
			throw new IllegalArgumentException();
		
		RecordObj record = (RecordObj) _data.get(video);
		if(record == null && change < 1) { //Video does not exist but change is bad, no record created
			throw new IllegalArgumentException();
		}
		else if(record == null) { //no video record is present i.e. a new record is created
			_data.put(video, new RecordObj(video, change, 0, 0));
		}
		else if(record.numOut > record.numOwned + change) {//Condition: numOut <= numOwned
			throw new IllegalArgumentException();
		}
		else if(record.numOwned + change < 1) { //change brings owned to 0 or negative i.e. video is removed
			_data.remove(video);
		}
		else { //Updates numOwned
			record.numOwned += change;
		}
	}

	/**
	 * Check out a video.
	 * 
	 * @param video
	 *            the video to be checked out.
	 * @throws IllegalArgumentException
	 *             if video has no record or numOut equals numOwned.
	 */
	void checkOut(Video video) {
		RecordObj record = (RecordObj) _data.get(video);

		// Checks conditions
		if (record == null || record.numOut == record.numOwned)
			throw new IllegalArgumentException();

		record.numOut++; // Increments number checked out
		record.numRentals++; // Increments number times EVER checked out

	}

	/**
	 * Check in a video.
	 * 
	 * @param video
	 *            the video to be checked in.
	 * @throws IllegalArgumentException
	 *             if video has no record or numOut non-positive.
	 */
	void checkIn(Video video) {
		RecordObj record = (RecordObj) _data.get(video);
		
		//Checks conditions
		if(record == null || record.numOut == 0)
			throw new IllegalArgumentException();
		
		record.numOut--; //Decrements number checked out
	}

	/**
	 * Remove all records from the inventory.
	 */
	void clear() {
		_data.clear();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Database:\n");
		for (Record r : _data.values()) {
			buffer.append("  ");
			buffer.append(r);
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Implementation of Record interface.
	 *
	 * <p>
	 * This is a utility class for Inventory. Fields are mutable and
	 * package-private.
	 * </p>
	 *
	 * <p>
	 * <b>Class Invariant:</b> No two instances may reference the same Video.
	 * </p>
	 *
	 * @see Record
	 */
	private static final class RecordObj implements Record {
		Video video; // the video
		int numOwned; // copies owned
		int numOut; // copies currently rented
		int numRentals; // total times video has been rented

		RecordObj(Video video, int numOwned, int numOut, int numRentals) {
			this.video = video;
			this.numOwned = numOwned;
			this.numOut = numOut;
			this.numRentals = numRentals;
		}

		public Video video() {
			return video;
		}

		public int numOwned() {
			return numOwned;
		}

		public int numOut() {
			return numOut;
		}

		public int numRentals() {
			return numRentals;
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(video);
			buffer.append(" [");
			buffer.append(numOwned);
			buffer.append(",");
			buffer.append(numOut);
			buffer.append(",");
			buffer.append(numRentals);
			buffer.append("]");
			return buffer.toString();
		}
	}
}
