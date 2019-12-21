/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Assignment 2
 * 27 January 2016
 */

import java.util.Arrays;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2Week2 
{
	
	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////
	
	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return( "Gill,Zachary,3487163");
	}
	
	//	Problem #1
	// Directions: Return true if the array contains, somewhere,
	// three increasing consecutive numbers like ....4, 5, 6,... or
	// 23, 24, 25.

	//	FindThreeIncreasingNumbers({1, 4, 5, 6, 2}) → true
	//	FindThreeIncreasingNumbers({1, 2, 3}) → true
	//	FindThreeIncreasingNumbers({1, 2, 4}) → false
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return
	 * 		returns true if there are three increasing consecutive numbers
	 * 		returns false if there are not three increasing consecutive numbers
	 */
	static boolean FindThreeIncreasingNumbers(int[] NumberList) 
	{
	    int count = 1; // stores the number of consecutive numbers
	    
	    for (int i = 1; i < NumberList.length; i++) { // for each number in the list
	        if (NumberList[i] - NumberList[i - 1] == 1) // if it comes directly after the last number
	            count++;
	        else
	            count = 1; //reset to 1 consecutive number

            if (count == 3) // 3 consecutive numbers
                return true;
	    }
	    
	    return false;
	}

	//	Problem #2
	//	For each multiple of 10 in the given array, change all the values 
	//	following it to be that multiple of 10, until encountering another 
	//	multiple of 10. So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.

	//	multiplesOfTen({2, 10, 3, 4, 20, 5}) → {2, 10, 10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 20, 2}) → {10, 10, 20, 20}
	//	multiplesOfTen({10, 1, 9, 20}) → {10, 10, 10, 20}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list of the same numbers changed to multiples of
	 * 		ten as they are encountered.
	 */
	static int[] multiplesOfTen(int[] NumberList) 
	{
        boolean hit = false; // flag to store if a multiple of ten has been hit
        
        for (int i = 0; i < NumberList.length; i++) { // for each number 
            if (NumberList[i] % 10 == 0) // if the number is a multiple of ten
                hit = true;
            else if (hit) // cant be true if i == 0
                NumberList[i] = NumberList[i - 1]; // set the current number to the multiple of ten
        }
        
        return NumberList;
	}	

	//	Problem #3
	//	We'll say that an element in an array is "alone" if there are 
	//	values before and after it, and those values are different 
	//	from it. Return a version of the given array where every instance 
	//	of the given value which is alone is replaced by whichever 
	//	value to its left or right is larger.

	//	CheckForAloneNumbers({1, 2, 3}, 2) → {1, 3, 3}
	//	CheckForAloneNumbers({1, 2, 3, 2, 5, 2}, 2) → {1, 3, 3, 5, 5, 2}
	//	CheckForAloneNumbers({3, 4}, 3) → {3, 4}
	
	/**
	 * 
	 * @param NumberList, changingNumber
	 * 		int[] list containing some numbers.
	 * 		int value of the number that should change in the array.
	 * 
	 * @return NumberList
	 * 		int[] list of numbers where every occurrence of changingNumber
	 * 		has been replaced by the larger of its two neighbors.
	 */
	static int[] CheckForAloneNumbers(int[] NumberList, int changingNumber) 
	{
	    for (int i = 1; i < NumberList.length - 1; i++) { // dont check the first or last numbers
	        if (NumberList[i] == changingNumber) { // if the number is the one we are looking for
    	        int left = NumberList[i - 1];
    	        int current = NumberList[i];
    	        int right = NumberList[i + 1];
    	        
    	        if (current != left && current != right) { //only do anything if the number is alone
    	            if (left > right) //if the left number is greater
    	                NumberList[i] = left; //replace with larger number
    	            else {
                        NumberList[i] = right; //replace with larger number
                    }
    	        }
	        }
	    }
	    
	    return NumberList;
	}	

	//	Problem #4
	//	Return a version of the given array where each zero value in 
	//	the array is replaced by the largest odd value to the right 
	//	of the zero in the array. If there is no odd value to the 
	//	right of the zero, leave the zero as a zero. 

	//	ReplaceZerosWithLargestOdd({0, 5, 0, 3}) → {5, 5, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 4, 0, 3}) → {3, 4, 3, 3}
	//	ReplaceZerosWithLargestOdd({0, 1, 0}) → {1, 1, 0}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return NumberList
	 * 		int[] list containing the numbers where the zeros have been
	 * 		replaced with the largest odd number to the right of them.
	 */
	public static int[] ReplaceZerosWithLargestOdd(int[] NumberList) 
	{
	    int odd = NumberList[0]; //stores the largest odd number
	    
	    for (int i = 0; i < NumberList.length; i++) { //for each number
	        if (NumberList[i] == odd) { //if you reach the largest odd number then get the new largest odd number or you dont have a largest odd yet
	            odd = Integer.MIN_VALUE; // stores the largest odd value to the right
	            for (int j = i + 1; j < NumberList.length; j++) { //even though it starts at i + 1, if i==0, the first number cant be an important odd number and also a 0 that would need to be changed
	                if (NumberList[j] % 2 == 1 && NumberList[j] > odd) { // if the number is odd
	                    odd = NumberList[j]; //set the new largest odd number
	                }
	            }
	            if (odd == Integer.MIN_VALUE)
	                return NumberList; // if there are no more odd numbers, remaining 0s will remain unchanged
	        }
	        
	        if (NumberList[i] == 0) { //if the number is 0
	            NumberList[i] = odd; //change it to the largest odd number
	        }
	    }
	    
	    return NumberList;
	}
	
	//	Problem #5
	//	Given start and end numbers, return a new array containing 
	//	the sequence of integers from start up to but not including end, 
	//	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number 
	//	will be greater or equal to the start number. 
	//	Note that a length-0 array is valid. 

	//	CreateIncreasingArray(5, 10) → {5, 6, 7, 8, 9}
	//	CreateIncreasingArray(11, 18) → {11, 12, 13, 14, 15, 16, 17}
	//	CreateIncreasingArray(1, 3) → {1, 2}	
	
	/**
	 * 
	 * @param start, end
	 * 		Two integers stating the start and end of the sequence.
	 * 
	 * @return NumberList
	 * 		int [] containg numbers ranging from start to end
	 * 		in order from least to greatest.
	 */
	static int[] CreateIncreasingArray(int start, int end) 
	{
	    int[] array = new int[end - start]; //create a new array of proper length
	    
	    for (int i = start; i < end; i++) //fill the array
	        array[i - start] = i;
	    
	    return array;
	}
	
	//	Problem #6
	//	Given a non-empty array of ints, return a new array containing 
	//	the elements from the original array that come before the 
	//	first 4 in the original array. The original array will contain 
	//	at least one 4. Note that it is valid in java to create 
	//	an array of length 0.  

	//	CopyNumbersBeforeFour({1, 2, 4, 1}) → {1, 2}
	//	CopyNumbersBeforeFour({3, 1, 4}) → {3, 1}
	//	CopyNumbersBeforeFour({1, 4, 4}) → {1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return AbridgedList
	 * 		int[] list containing all the numbers that appeared
	 * 		before the first 4 in the array.
	 */
	static int[] CopyNumbersBeforeFour(int[] NumberList) 
	{
	    int[] array = new int[0]; //create new array
	    
	    for (int i = 0; i < NumberList.length; i++) {
	        if (NumberList[i] == 4) { //if you find the first 4
	            array = new int[i]; //create array of appropriate size
	            for (int j = 0; j < i; j++)
	                array[j] = NumberList[j]; //copy array elements
	            return array; //nothing else needs to be done
	        }
	    }
	    
	    return array;
	}
	
	//	Problem #7
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the zeros 
	//	are grouped at the start of the array. The order of the 
	//	non-zero numbers does not matter. So {1, 0, 0, 1} becomes 
	//	{0 ,0, 1, 1}. You may modify and return the 
	//	given array or make a new array.  

	//	MoveZerosToFront({1, 0, 0, 1}) → {0, 0, 1, 1}
	//	MoveZerosToFront({0, 1, 1, 0, 1}) → {0, 0, 1, 1, 1}
	//	MoveZerosToFront({1, 0}) → {0, 1}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the zeros moved to the front.
	 */
	static int[] MoveZerosToFront(int[] NumberList) 
	{
	    int[] array = new int[NumberList.length]; //create array with proper number of elements
	    
	    int count = 0; // stores the number of copied elements
	    for (int i = 0; i < NumberList.length; i++) {
	        if (NumberList[i] != 0) {
	            count++;
	            array[NumberList.length - count] = NumberList[i]; //push nonzero elements to the back of the array, leaving the unset 0s at the beginning
	        }
	    }
	    
	    return array;
	}
	
	//	Problem #8
	//	Return an array that contains the exact same numbers as 
	//	the given array, but rearranged so that all the even numbers 
	//	come before all the odd numbers. Other than that, the 
	//	numbers can be in any order. You may modify and 
	//	return the given array, or make a new array.  

	//	EvenFrontOddBack({1, 0, 1, 0, 0, 1, 1}) → {0, 0, 0, 1, 1, 1, 1}
	//	EvenFrontOddBack({3, 3, 2}) → {2, 3, 3}
	//	EvenFrontOddBack({2, 2, 2}) → {2, 2, 2}
	
	/**
	 * 
	 * @param NumberList
	 * 		int[] list containing some numbers.
	 * 
	 * @return RearrangedList
	 * 		int[] list containing all the numbers from original
	 * 		list with the even numbers in the front and the
	 * 		odd numbers in the back.
	 */
	static int[] EvenFrontOddBack(int[] NumberList) 
	{
        int[] array = new int[NumberList.length]; //create array with proper number of elements
        
        int evens = 0; //stores the number of even elements found
        int odds = 0; //stores the number of odd elements found
        for (int i = 0; i < NumberList.length; i++) {
            if (NumberList[i] % 2 == 0) { //if the number is even
                array[evens] = NumberList[i]; //put the element towards the front of the array
                evens++;
            }
            else { //if the number is odd
                odds++;
                array[NumberList.length - odds] = NumberList[i]; //put the element towards the back of the array
            }
        }
        
        return array;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
	    
	    //Personal Tests
	    
//	    System.out.println(FindThreeIncreasingNumbers(new int[] {1, 4, 5, 6, 2})); // true
//	    System.out.println(FindThreeIncreasingNumbers(new int[] {1, 2, 3})); // true
//	    System.out.println(FindThreeIncreasingNumbers(new int[] {1, 2, 4})); // false
//
//	    System.out.println(Arrays.toString(multiplesOfTen(new int[] {2, 10, 3, 4, 20, 5}))); // {2, 10, 10, 10, 20, 20}
//	    System.out.println(Arrays.toString(multiplesOfTen(new int[] {10, 1, 20, 2}))); // {10, 10, 20, 20}
//	    System.out.println(Arrays.toString(multiplesOfTen(new int[] {10, 1, 9, 20}))); // {10, 10, 10, 20}
//
//	    System.out.println(Arrays.toString(CheckForAloneNumbers(new int[] {1, 2, 3}, 2))); // {1, 3, 3}
//	    System.out.println(Arrays.toString(CheckForAloneNumbers(new int[] {1, 2, 3, 2, 5, 2}, 2))); // {1, 3, 3, 5, 5, 2}
//      System.out.println(Arrays.toString(CheckForAloneNumbers(new int[] {3, 4}, 3))); // {3, 4}
//      System.out.println(Arrays.toString(CheckForAloneNumbers(new int[] {3, 4, 3, 7, 1, 3, 3}, 3)));
//
//	    System.out.println(Arrays.toString(ReplaceZerosWithLargestOdd(new int[] {0, 5, 0, 3}))); // {5, 5, 3, 3}
//	    System.out.println(Arrays.toString(ReplaceZerosWithLargestOdd(new int[] {0, 4, 0, 3}))); // {3, 4, 3, 3}
//      System.out.println(Arrays.toString(ReplaceZerosWithLargestOdd(new int[] {0, 1, 0}))); // {1, 1, 0}
//      System.out.println(Arrays.toString(ReplaceZerosWithLargestOdd(new int[] {0, 5, 0, 9, 0, 0, 1, 0, 7})));
//
//	    System.out.println(Arrays.toString(CreateIncreasingArray(5, 10))); // {5, 6, 7, 8, 9}
//	    System.out.println(Arrays.toString(CreateIncreasingArray(11, 18))); // {11, 12, 13, 14, 15, 16, 17}
//	    System.out.println(Arrays.toString(CreateIncreasingArray(1, 3))); // {1, 2}  
//
//	    System.out.println(Arrays.toString(CopyNumbersBeforeFour(new int[] {1, 2, 4, 1}))); // {1, 2}
//	    System.out.println(Arrays.toString(CopyNumbersBeforeFour(new int[] {3, 1, 4}))); // {3, 1}
//	    System.out.println(Arrays.toString(CopyNumbersBeforeFour(new int[] {1, 4, 4}))); // {1}
//      System.out.println(Arrays.toString(CopyNumbersBeforeFour(new int[] {1, 7, 9, 12, 3, 5, 6})));
//
//	    System.out.println(Arrays.toString(MoveZerosToFront(new int[] {1, 0, 0, 1}))); // {0, 0, 1, 1}
//	    System.out.println(Arrays.toString(MoveZerosToFront(new int[] {0, 1, 1, 0, 1}))); // {0, 0, 1, 1, 1}
//      System.out.println(Arrays.toString(MoveZerosToFront(new int[] {1, 0}))); // {0, 1}
//      System.out.println(Arrays.toString(MoveZerosToFront(new int[] {1, 0, 0, 0, 5, 3, 1, 4, 0, 0})));
//
//	    System.out.println(Arrays.toString(EvenFrontOddBack(new int[] {1, 0, 1, 0, 0, 1, 1}))); // {0, 0, 0, 1, 1, 1, 1}
//	    System.out.println(Arrays.toString(EvenFrontOddBack(new int[] {3, 3, 2}))); // {2, 3, 3}
//	    System.out.println(Arrays.toString(EvenFrontOddBack(new int[] {2, 2, 2}))); // {2, 2, 2}
//      System.out.println(Arrays.toString(EvenFrontOddBack(new int[] {2, 1, 2, 15, 4, 7, 0, 6, 9})));

	}
	
}