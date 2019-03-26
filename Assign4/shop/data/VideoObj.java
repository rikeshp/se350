package shop.data;

/**
 * Implementation of Video interface.
 * 
 * @see Data
 */
final class VideoObj implements Video {
	private final String _title;
	private final int _year;
	private final String _director;

	/**
	 * Initialize all object attributes.
	 */
	VideoObj(String title, int year, String director) {
		_title = title.trim();
		_director = director.trim();
		_year = year;
	}

	public String director() {
		return _director;
	}

	public String title() {
		return _title;
	}

	public int year() {
		return _year;
	}

	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof VideoObj))
			return false;

		// Cast onto object
		VideoObj that = (VideoObj) thatObject;

		return that._director.equals(_director) && that._title.equals(_title) && that._year == _year;

	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + _title.hashCode();
		result = 37 * result + _year;
		result = 37 * result + _director.hashCode();

		return result;
	}

	public int compareTo(Object thatObject) {
		if(thatObject == null)
			throw new NullPointerException();
		if (!(thatObject instanceof VideoObj))
			throw new ClassCastException();

		// Cast onto object
		VideoObj that = (VideoObj) thatObject;

		int titleCompare = _title.compareTo(that._title);
		if (titleCompare != 0)
			return titleCompare;

		int yearCompare = _year - that._year;
		if (yearCompare != 0)
			return yearCompare;

		int directorCompare = _director.compareTo(that._director);
		if (directorCompare != 0)
			return directorCompare;

		return 0;
	}

	public String toString() {
		String build = _title + " (" + _year + ") : " + _director;
		return build;
	}
}
