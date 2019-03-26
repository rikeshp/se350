import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

// TODO:  complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data;

  InventorySet() {
    _data = new HashMap<VideoObj,Record>();
  }

  /**
   * Return the number of Records.
   */
  public int size() {
    return _data.size();
  }

  /**
   * Return a copy of the record for a given Video.
   */
  public Record get(VideoObj v) {
	if(_data.containsKey(v))
		return _data.get(v).copy();
	return null;
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
	ArrayList<Record> records = new ArrayList<Record>();
	for(Record record : _data.values())
		records.add(record.copy());
    return records;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
	
	if(video == null || change == 0) //Bad params
		throw new IllegalArgumentException();
	
	Record record = _data.get(video);
	if(record == null && change < 1) { //Video does not exist but change is bad, no record created
		throw new IllegalArgumentException();
	}
	else if(record == null && change > 0) { //no video record is present i.e. a new record is created
		Record insert = new Record(video, change, 0, 0);
		_data.put(video, insert);
	}
	else if(record.numOwned + change < 1) { //change brings owned to 0 or negative i.e. video is removed
		_data.remove(video);
	}
	else if(record.numOut > record.numOwned + change) {//Condition: numOut <= numOwned
		throw new IllegalArgumentException();
	}
	else { //Updates numOwned
		record.numOwned += change;
	}
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
	Record record = _data.get(video);
	
	//Checks conditions
	if(record == null || record.numOut == record.numOwned)
		throw new IllegalArgumentException();
	
	_data.get(video).numOut++; //Increments number checked out
	_data.get(video).numRentals++; //Increments number times EVER checked out
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
	Record record = _data.get(video);
	
	//Checks conditions
	if(record == null || record.numOut == 0)
		throw new IllegalArgumentException();
	
	_data.get(video).numOut--; //Decrements number checked out
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
	_data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   */
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
}
