package homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class hw2Test {
	
	@Test
	void testMap() {
		List<String> names = new ArrayList<String>();
		
		names.add("Terry");
		names.add("Torry");
		names.add("Tarry");
		names.add("Trart");
		
		List<Integer> hashCodes = new ArrayList<Integer>();
		for(int x = 0; x < names.size(); x++)
			hashCodes.add(names.get(x).hashCode());
			
		Function<String, Integer> f = (str) ->{
			return(str.hashCode());
		};
		
		List<Integer> result = new ArrayList<Integer>();
		result = hw2.map(names, f);
		
		for(int x = 0; x < hashCodes.size(); x++)
			assertEquals(hashCodes.get(x), result.get(x));
	}
	
	@Test
    void testFoldLeft(){
        List<Integer> l = new ArrayList<Integer>();
        
        for(int x = 0; x < 5; x++)
        	l.add(x);
        
        BiFunction<String, Integer, String> f = (p, q) ->{
        	return (p + "[" + q + "]");
        };
        
        assertEquals("@[0][1][2][3][4]", hw2.foldLeft("@", l, f));
        assertEquals("@", hw2.foldLeft("@", null, f));
    }
	
	@Test
	void testFoldRight() {
		List<Integer> a = new ArrayList<Integer>();
		
		for (int x = 0; x < 5; x++) {
			a.add(x);
		}
		
		BiFunction<Integer, String, String> f = (p, q) -> {
			return (q + "[" + p + "]");
		};

        assertEquals("@", hw2.foldRight("@", null, f));
        assertEquals("@[4][3][2][1][0]", hw2.foldRight("@", a, f));
	}
	
	 @Test
	    void testFilter(){
	        List<Person> employees = new ArrayList<Person>();
	        
	        employees.add(new Person(1, "Under1"));
	        employees.add(new Person(2, "Under2"));
	        employees.add(new Person(100001, "Over1"));
	        employees.add(new Person(111111, "Over2"));

	        Predicate<Person> greaterThan100k = (Person person) -> person.getSalary() < 100000;
	        employees = hw2.filter(employees, greaterThan100k);
	        
	        String result = "";
	        for(Person p : employees){
	            result += p.name();
	        }
	        
	        assertEquals("Over1Over2", result);
	    }
	
	
	@Test
	void testBinFoldLeft() {
		
        BiFunction<Integer, Integer, Integer> f = (e, q) ->{
        	if(q == 5)
        		return e + 1;
        	else
        		return e;
        };
        
        List<Integer> l = new ArrayList<Integer>();
        l.add(5);
        
        assertEquals(Integer.valueOf(1), hw2.binFoldLeft(0, l, f));
        
        l.clear();
        
        for(int x = 0; x < 100; x++)
        	if(x % 5 == 0)
        		l.add(5);
        	else
        		l.add(x);
        
        
        assertEquals(Integer.valueOf(20), hw2.binFoldLeft(0, l, f));
	}
}
