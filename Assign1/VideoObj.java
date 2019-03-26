// TODO:  complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
	//Bad params
	if(title == null
    		|| director == null
    		|| year <= 1800
    		|| year >= 5000)
    	throw new IllegalArgumentException();
	
	//Initializes all attributes and trims 
	this._title = title.trim();
	this._year = year;
	this._director = director.trim();
	
	//Title or director are empty strings
	if("".equals(_title) || "".equals(_director))
		throw new IllegalArgumentException();
     
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
	if(!(thatObject instanceof VideoObj))
			return false;
	
	//Cast onto object
	VideoObj that = (VideoObj) thatObject;
	
    return that._director.equals(_director) && that._title.equals(_title) && that._year == _year;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
	int result = 17;
	result = 37 * result + _title.hashCode();
	result = 37 * result + _year;
	result = 37 * result + _director.hashCode();
	
    return result;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject) {
	if(!(thatObject instanceof VideoObj))
		throw new ClassCastException();
	
	//Cast onto object
	VideoObj that = (VideoObj) thatObject;
	
	int titleCompare = _title.compareTo(that._title);
	if(titleCompare != 0)
		return titleCompare;
	
	int yearCompare = _year - that._year;
	if(yearCompare != 0)
		return yearCompare;
	
	int directorCompare = _director.compareTo(that._director);
	if(directorCompare != 0)
		return directorCompare;
	
	return 0;
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
	String build = _title + " (" + _year + ") : " + _director;
    return build;
  }
}
