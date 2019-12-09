/**
 * 
 */
package com.softtek.java.academy.collections.basic.examples;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alejandro
 *
 */
public class JavaMapBasic {
	public static void main(String[] args) {
	      Map <Integer, String> namesMap = new HashMap<>(); 
//	      namesMap.put("Zara", "8");
//		  namesMap.put("Mahnaz", "31");
//		  namesMap.put("Ayan", "12");
//		  namesMap.put("Daisy", "14");
	      namesMap.put(8, "Zara");
		  namesMap.put(31, "Mahnaz");
		  namesMap.put(12, "Ayan");
		  namesMap.put(14, "Daisy");
		  namesMap.put(8, "Donald");
		  
		  
		  System.out.println();
		  System.out.println(" Map Elements");
		  System.out.print("\t" + namesMap);
		  System.out.println("\nZara value: " + namesMap.get(12));
	}
}
