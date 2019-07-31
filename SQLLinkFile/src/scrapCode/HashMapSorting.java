/**
 * 
 */
package scrapCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @author gce
 *
 */
public class HashMapSorting {
	 static int temp;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
	    map.put(Arrays.asList(0, 1, 2, 3, 4), 34);
	    map.put(Arrays.asList(10, 11, 12, 13, 14), 25);
	    map.put(Arrays.asList(20, 21, 22, 23, 24), 50);
	    map.put(Arrays.asList(30, 31, 32, 33, 34), 70); // "duplicate" value
	    map.put(Arrays.asList(15, 16, 17, 18, 19), 34);
	    map.put(Arrays.asList(5, 6, 7, 8, 9), 25);
	    map.put(Arrays.asList(25, 26, 27, 28, 29), 54);
	    map.put(Arrays.asList(40, 41, 42, 43, 44), 50); // "duplicate" value
	    map.put(Arrays.asList(35, 36, 37, 38, 39), 34);
	    map.put(Arrays.asList(45, 46, 47, 48, 49), 25);
	    map.put(Arrays.asList(55, 56, 57, 58, 59), 50);
	    map.put(Arrays.asList(50, 51, 52, 53, 54), 50); // "duplicate" value
	    
	   System.out.println(map);
	   System.out.println(SortStingsNumericly(map));
	}

	static <K, V extends Comparable<? super V>> List<Entry<String, Integer>> entriesSortedByValues(Map<String, Integer> map) {

	    List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String, Integer>>(map.entrySet());

	    Collections.sort(sortedEntries, new Comparator<Entry<String, Integer>>() {
	        @Override
	        public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
	            return e1.getKey().compareTo(e2.getKey());
	        }
	    });

	    return sortedEntries;
	}
	
	static Map<String, Integer> SortStingsNumericly(Map<List<Integer>, Integer> map) {
		 Map<String, Integer> sortedEntries = new LinkedHashMap<String, Integer>();
		 int[] StoredSort = new int[map.size()];
		 int i=0;
		 for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
			 StoredSort[i]=entry.getKey().get(0);
			 i++;
		}
		 System.out.println(Arrays.toString(StoredSort));
		sort(StoredSort, "lo"); 
		System.out.println(Arrays.toString(StoredSort));
		
		for (int j=0; j<StoredSort.length; j++) {
			 for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
				 if(entry.getKey().get(0)==StoredSort[j]) {
					 sortedEntries.put(entry.getKey().toString(), map.get(entry.getKey()));
				 }
			 }
		}
			
		sortedEntries.entrySet()
		.stream()
		.forEach(System.out::println);
		
		return sortedEntries;
	}
	
	private static void sort(int[] array, String order) {
		if (order.contentEquals("hi")) {
			// sort highest value to lowest value
			for (int i = 0; i < array.length; i++) { //create a variable for each value of the array
				for (int j = i + 1; j < array.length; j++) { //create a comparison variable for each other value of the array
					if (array[i] < array[j]) { //comparison statement changes with each type of sort
						temp = array[i]; //Store i value in a temp variable
						array[i] = array[j]; //posesion B = posesion A and posesion B = posesion B
						array[j] = temp; //posesion B = temp variable to complete the swap
					}
				}
			}
		} else if (order.contentEquals("lo")) {
			// sort lowest value to highest value
			for (int i = 0; i < array.length; i++) { //create a variable for each value of the array
				for (int j = i + 1; j < array.length; j++) { //create a comparison variable for each other value of the array
					if (array[i] > array[j]) { //comparison statement changes with each type of sort
						temp = array[i];  //Store i value in a temp variable
						array[i] = array[j];  //posesion B = posesion A and posesion B = posesion B
						array[j] = temp; //posesion B = temp variable to complete the swap
					}
				}
			}
		}	
	}
}
