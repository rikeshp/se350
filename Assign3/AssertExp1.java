package homework3;

// for comparing arrays in main() tests only
import java.util.*;

public class AssertExp1 {

	/**
	 * minValue returns the minimum value in an array of doubles. You can assume
	 * the array is nonempty and has no duplicates. Your solution must go
	 * through the array exactly once. Your solution must not call any other
	 * functions. Here are some examples (using "==" informally):
	 * 
	 * -7 == minValue(new double[] { -7 }) -7 == minValue(new double[] { 1, -4,
	 * -7, 7, 8, 11 }) -13 == minValue(new double[] { -13, -4, -7, 7, 8, 11 })
	 * -9 == minValue(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 * 
	 * @param list the list containing values
	 * @precondition list is non-null and non-empty
	 * @postcondition the minimum value is the first element of the sorted array, least to greatest
	 * @return double of the minimum value
	 */
	public static double minValue(double[] list) {
		assert list != null; //List can't be null
		assert list.length > 0; //List has at least 1 element
		
		double min = list[0]; //Placeholder value
		
		//Loop goes through each element and checks against current min value. Stores if it is smaller
		for (int i = 0; i < list.length; ++i) 
			if (list[i] < min)
				min = list[i];
		
		Arrays.sort(list); //Sorts list from least to greatest
		assert min == list[0]; //Smallest value should be index 0 now. Checks for that
		
		return min;
	}

	/**
	 * minPosition returns the position of the minimum value in an array of
	 * doubles. The first position in an array is 0 and the last is the
	 * array.length-1. You can assume the array is nonempty and has no
	 * duplicates. Your solution must go through the array exactly once. Your
	 * solution must not call any other functions. Here are some examples (using
	 * "==" informally):
	 * 
	 * 0 == minPosition(new double[] { -7 }) 2 == minPosition(new double[] { 1,
	 * -4, -7, 7, 8, 11 }) 0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11
	 * }) 6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 * 
	 * @param list the list of values
	 * @precondition list is non-null and non-empty
	 * @postcondition the value of the minPos is <code>minValue(list)</code>
	 * @return integer of the minimum position i.e. index containing the minimum value
	 */
	public static int minPosition(double[] list) {
		assert list != null; //List cannot be null
		assert list.length > 0; //List has at least 1 element
		
		double min = list[0]; //Stores minimum value, placeholder
		int minPos = 0; //Stores minimum position/index, placeholder
		
		//Loop to check for minimum value.
		//If new min is found, minPos is updated with current index
		for (int x = 0; x < list.length; x++) {
			if (list[x] < min) {
				min = list[x];
				minPos = x;
			}
		}
		
		assert list[minPos] == minValue(list); //assert minPos value is same as minValue
		return minPos;
	}

	/**
	 * numUnique returns the number of unique values in an array of doubles.
	 * Unlike the previous questions, the array may be empty and it may contain
	 * duplicate values. Also unlike the previous questions, you can assume the
	 * array is sorted.
	 * 
	 * Your solution must go through the array exactly once. Your solution must
	 * not call any other functions. Here are some examples (using "=="
	 * informally):
	 * 
	 * 0 == numUnique(new double[] { }) 1 == numUnique(new double[] { 11 }) 1 ==
	 * numUnique(new double[] { 11, 11, 11, 11 }) 8 == numUnique(new double[] {
	 * 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }) 8
	 * == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,
	 * 77, 88 })
	 * 
	 * @param list the list of values
	 * @precondition list is non-null
	 * @postcondition the count of unique values matches the length of distinct values array
	 * @return integer of amount of unique numbers
	 */
	public static int numUnique(double[] list) {
		assert list != null; //List cannot be null
		int len = list.length;
		if(len == 0) //Empty list
			return 0;
		else if(len == 1) //Singleton
			return 1;
		
		int count = 1; //Stores number of unique
		
		//Loop to check for number of uniques.
		//Because list is sorted a unique number is detected when there's a change in value.
		for(int x = 0; x < len - 1; x++) {
			if(list[x] != list[x + 1]) {
				count++;
			}
		}
		assert count == Arrays.stream(list).distinct().toArray().length; //Returns the number of unique elements of an array.
		
		return count;
	}

	/**
	 * removeDuplicates returns the number of unique values in an array of
	 * doubles. You may assume that the list is sorted, as you did for
	 * numUnique.
	 * 
	 * Your solution may call numUnique, but should not call any other
	 * functions. After the call to numUnique, you must go through the array
	 * exactly one more time. Here are some examples (using "==" informally):
	 * 
	 * new double[] { } == removeDuplicates(new double[] { }) new double[] { 11
	 * } == removeDuplicates(new double[] { 11 }) == removeDuplicates(new
	 * double[] { 11, 11, 11, 11 }) new double[] { 11, 22, 33, 44, 55, 66, 77,
	 * 88 } == removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44,
	 * 44, 44, 44, 55, 55, 66, 77, 88, 88 }) == removeDuplicates(new double[] {
	 * 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 * 
	 * @param list the list of values
	 * @precondition list is non-null
	 * @postcondition the array length of unique numbers matches the original <code>numUnique(list)</code> value
	 * @return double[] containing unique numbers
	 */
	public static double[] removeDuplicates(double[] list) {
		assert list != null; //List cannot be null
		int len = list.length;
		
		if(len == 0) //Empty list
			return new double[] {};
		else if (len == 1) //Singleton
			return new double[] {list[0]};
		
		int numUnique = numUnique(list); //Stores amount of unique numbers
		
		double[] unique = new double[numUnique]; //Creates array to store the unique numbers
		unique[0] = list[0]; //First element is always unique and can be used for comparisons
		int pos = 1; //Position tracker
		
		for(int x = 1; x < len; x++) {
			if(list[x] != list[x - 1]) {
				unique[pos] = list[x];
				pos++;
			}
		}
		assert unique.length == numUnique;
		
		return unique;
	}

}
