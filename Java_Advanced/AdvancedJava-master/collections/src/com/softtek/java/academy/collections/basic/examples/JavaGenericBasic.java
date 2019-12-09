/**
 * 
 */
package com.softtek.java.academy.collections.basic.examples;

/**
 * @author Alejandro
 *
 */
public class JavaGenericBasic {
   // generic method printArray                         
   public static < E,K > void printArray( E[] inputArray , K number)
   {
      // Display array elements              
         for ( E element : inputArray ){        
            System.out.printf( "%s %s", element, number );
         }
         System.out.println();
    }

    public static void main( String args[] )
    {
        // Create arrays of Integer, Double and Character
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println( "Array integerArray contains:" );
        printArray( intArray, 2  ); // pass an Integer array

        System.out.println( "\nArray doubleArray contains:" );
        printArray( doubleArray, 2.2 ); // pass a Double array

        System.out.println( "\nArray characterArray contains:" );
        printArray( charArray, "algo" ); // pass a Character array
    } 
}
