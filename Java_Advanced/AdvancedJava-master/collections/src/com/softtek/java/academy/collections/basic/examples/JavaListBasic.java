/**
 * 
 */
package com.softtek.java.academy.collections.basic.examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alejandro
 * 
 * A Very Basic Example of the use of the List Interface
 *
 */
public class JavaListBasic {
	public static void main(String[] args) {
		  // Using Generic
	      final List<String> namesList = new ArrayList<String>();
	      namesList.add("Zara");
	      namesList.add("Mahnaz");
	      namesList.add("Ayan");
	      namesList.add(1, "Softtek");
	      namesList.set(namesList.indexOf("Zara"),"Victor");
	      
	      Iterator<String> i = namesList.iterator();
	      
	      while (i.hasNext()) {
	    	  String name = i.next();
	    	  System.out.println(" Element : " + name);
	      }
	      
	      System.out.println(" Contains Ayan: " + namesList.contains("Ayan"));
	      System.out.println(" ArrayList Elements");
	      System.out.print("\t" + namesList);
	      
	      // No Generic Usage
	      List linkedList = new LinkedList();
	      linkedList.add("Zara");
	      linkedList.add(1);
	      linkedList.add("Ayan");
	      System.out.println();
	      System.out.println(" LinkedList Elements");
	      System.out.print("\t" + linkedList);
	   }
}
