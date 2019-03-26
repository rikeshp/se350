package homework2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Terry Yu
 * 
 *
 */
public class hw2 {
	
	/**
	 * Given a List of objects return a List of hashCodes using the BiFunction f.
	 * @param l list containing objects of type U
	 * @param f BiFunction to be passed
	 * @return a list of BiFunction results
	 */
	public static <U, V> List<V> map(Iterable<U> l, Function<U, V> f) {
		List<V> result = new ArrayList<V>(); // Stores the values after hashing with f

		// Loop to hash each function with f and store into result
		for (Iterator<U> iter = l.iterator(); iter.hasNext();) {
			result.add(f.apply(iter.next()));
		}

		return result;
	} // map()

	/**
	 * Given a list of objects perform the given BiFunction f on it
	 * @param e Value to 
	 * @param l List to perform operation on
	 * @param f BiFunction to use in operation
	 * @return a concatenated/summed result depending on BiFunction
	 */
	public static <U, V> V foldLeft(V e, Iterable<U> l, BiFunction<V, U, V> f) {
		if (l != null) {
			if (l.iterator().hasNext() && l != null) { // Determines if there is a next element
				List<U> tail = new ArrayList<>(); // Will store the remaining elements in the iterable
				l.forEach(tail::add); // Populates list with remaining

				// Does the foldLeft operation
				return foldLeft(f.apply(e, l.iterator().next()), tail.subList(1, tail.size()), f);
			} else
				return e;
		} else
			return e;
	}

	/**
	 * Given a list of objects, perform the given BiFunction f on it
	 * @param e Value to 
	 * @param l List to perform operation on
	 * @param f BiFunction to use in operation
	 * @return a concatenated/summed result depending on BiFunction
	 */
	public static <U, V> V foldRight(V e, List<U> l, BiFunction<U, V, V> f) {
		if (l != null) {
			if (l.iterator().hasNext()) {
				U head = l.iterator().next();
				return f.apply(head, foldRight(e, l.subList(1, l.size()), f));
			} else
				return e;
		} else
			return e;
	}

    /**
     * Given a list of objects, filter() will filter out those that pass the Predicate p tests
     * @param l, list to perform the Predicate tests on
     * @param p, Predicate to test with
     * @return a list containing the objects that were not filtered out
     */
	public static <U> List<U> filter(List<U> l, Predicate<U> p) {

		// Loop through each object
		for (Iterator<U> iter = l.iterator(); iter.hasNext();) {
			U value = iter.next(); // Gets object
			if (p.test(value)) { // Test value and removes if necessary
				iter.remove();
			}
		}

		return l; // Filtered list
	}
	
	/**
	 * Given a list it will determine the number of occurrences of e in l
	 * @param e object to match against
	 * @param l list of objects to search through
	 * @param f BiFunction to test with
	 * @return the number of occurrences of e in l
	 */
	public static <U> U binFoldLeft(U e, List<U> l, BiFunction<U, U, U> f) {
		if(l.size() == 1) //Base case
			return f.apply(e, l.iterator().next());
		
	    U result = e; //Stores occurrences
	    result = binFoldLeft(result, (l.subList(0, l.size()/2)), f); //Check left side
	    result = binFoldLeft(result, (l.subList(l.size()/2, l.size())),f); //Check right side
	    return result; //Return occurrences
	}
	
	
	public static void main(String[] args) {
		// Use map to implement the following behavior (described in Python).  i.e
		// given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		// names[i] = hash(names[i])

		// Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)

		// Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)

		// consider an array of Persons. Use filter to
		// print the names of the Persons whose salary is
		// greater than 100000

		// Use binFoldLeft to find the number of occurrences
		// of 5 in an array of integers

	}

}

class Person {
	final int salary;
	final String name;

	Person(int salary, String name) {
		this.salary = salary;
		this.name = name;
	}

	int getSalary() {
		return salary;
	}

	String name() {
		return name;
	}

}