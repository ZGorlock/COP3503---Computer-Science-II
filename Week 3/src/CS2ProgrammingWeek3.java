/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Assignment 3
 * 3 February 2016
 */

import java.awt.Frame;
import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek3 
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
	//	Given a string and a non-empty substring sub, compute recursively if at 
	//	least n copies of sub appear in the string somewhere, possibly with 
	//	overlapping. N will be non-negative.

	//	subCopies("catcowcat", "cat", 2) → true
	//	subCopies("catcowcat", "cow", 2) → false
	//	subCopies("catcowcat", "cow", 1) → true
	
	/**
	 * 
	 * @param str, sub, n
	 * 		String str contains the original string to be tested against
	 * 		String sub contains the string that is used to test against str
	 * 		int n specifies how many copies of sub to check for
	 * 
	 * @return
	 * 		returns true if there are n copies of sub in str
	 * 		returns false if there are not n copies of sub in str
	 */
	static boolean subCopies(String str, String sub, int n) 
	{
	    if (str.length() < sub.length()) //base case
	        return n <= 0;
	    
	    if (str.substring(0, sub.length()).equals(sub)) { //test if sub is the beginning of str
	        n--;
	        if (n == 0) //test for early return, as long as it has "at least" n copies it is true
	            return true;
	    }
	    
	    return subCopies(str.substring(1), sub, n); //remove the first character in str and test again
	}

	//	Problem #2
	//	Given a non-negative int n, return the sum of its digits recursively 
	//	(no loops). Note that mod (%) by 10 yields the rightmost 
	//	digit (126 % 10 is 6), while divide (/) by 10 removes the 
	//	rightmost digit (126 / 10 is 12).

	//	sumDigitsInNumber(126) → 9
	//	sumDigitsInNumber(49) → 13
	//	sumDigitsInNumber(12) → 3
	
	/**
	 * 
	 * @param n
	 * 		int n contains integer to deal with.
	 * 
	 * @return integer
	 * 		integer that is the sum of each digit in int n.
	 */
	static int sumDigitsInNumber(int n) 
	{
	    if (n < 10) //base case: if there is only one digit 
	        return n;
	    
	    return (n % 10) + sumDigitsInNumber(n / 10); //get the rightmost digit and add the sum of the number without its rightmost digit
	}	

	//	Problem #3
	//	Given base and n that are both 1 or more, compute recursively (no loops) 
	//	the value of base to the n power, so powerN(3, 2) is 9 (3 squared).

	//	exponential(3, 1) → 3
	//	exponential(3, 2) → 9
	//	exponential(3, 3) → 27
	
	/**
	 * 
	 * @param base, n
	 * 		int base containing the base of the term
	 * 		int n containing the exponent of the term
	 * 
	 * @return integer
	 * 		integer that corresponds with equating base to the n power
	 */
	static int exponential(int base, int n) 
	{
	    if (n == 1) //base case, anything to the power of 1 is itself
	        return base;
	    
	    return base * exponential(base, --n); //multiply the base by itself and remove one from the exponent
	}	

	//	Problem #4
	//	Given a string, compute recursively (no loops) a new string 
	//	where all the lowercase 'x' chars have been changed to 'y' chars. 

	//	changeXtoY("codex") → "codey"
	//	changeXtoY("xxhixx") → "yyhiyy"
	//	changeXtoY("xhixhix") → "yhiyhiy"
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of chars to deal with
	 * 
	 * @return String
	 * 		String of characters where the lowercase x's have been changed to y's
	 */
	public static String changeXtoY(String str) 
	{
	    if (str.length() == 0) //base case: if the string is empty
	        return "";
	    String letter = str.substring(0, 1); //get the first letter of the string
	    
	    if (letter.equals("x")) //if the first letter is an x
	        letter = "y"; //change it to a y
	    
	    return letter + changeXtoY(str.substring(1)); //concatenate the new first letter with the return of the function on the rest of the string
	}
	
	//	Problem #5
	//	Given an array of ints, compute recursively if the array 
	//	contains a 6. We'll use the convention of considering only 
	//	the part of the array that begins at the given index. 
	//	In this way, a recursive call can pass index+1 to move down 
	//	the array. The initial call will pass in index as 0. 

	//	find6({1, 6, 4}, 0) → true
	//	find6({1, 4}, 0) → false
	//	find6({6}, 0) → true	
	
	/**
	 * 
	 * @param nums, index
	 * 		int[] list containing the original array of numbers
	 * 		int containing the position to start in nums
	 * 
	 * @return boolean
	 * 		returns true if a 6 is found in the array
	 * 		returns false if no 6 is found in the array
	 */
	static boolean find6(int[] nums, int index) 
	{
	    if (index >= nums.length) //base case
	        return false;
	    
	    if (nums[index] == 6) //if you found it
	        return true;
	    
	    return find6(nums, ++index); //look in the rest of the array
	}
	
	//	Problem #6
	//	Given a string, compute recursively a new string where all 
	//	the adjacent chars are now separated by a "*".   

	//	insertAsterisk("hello") → "h*e*l*l*o"
	//	insertAsterisk("abc") → "a*b*c"
	//	insertAsterisk("ab") → "a*b"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return AbridgedList
	 * 		String with an asterisk between each char
	 */
	static String insertAsterisk(String str) 
	{
	    if (str.length() <= 1) //base case
	        return str;
        
	    return str.substring(0, 1) + "*" + insertAsterisk(str.substring(1)); //otherwise add a * after that char and move on
	    
	}
	
	//	Problem #7
	//	We'll say that a "pair" in a string is two instances of 
	//	a char separated by a char. So "AxA" the A's make a pair. 
	//	Pair's can overlap, so "AxAxA" contains 3 pairs -- 2 for 
	//	A and 1 for x. Recursively compute the number of 
	//	pairs in the given string.  

	//	numberPairs("axa") → 1
	//	numberPairs("axax") → 2
	//	numberPairs("axbx") → 1
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars provided
	 * 
	 * @return 
	 * 		int with the number of pairs as defined above
	 */
	static int numberPairs(String str) 
	{
	    if (str.length() < 3) //base case
	        return 0;
	    
	    if (str.charAt(0) == str.charAt(2)) //if this character has a pair
	        return 1 + numberPairs(str.substring(1)); //return 1 plus the number of pairs in the rest of the string
	    
	    return numberPairs(str.substring(1)); //otherwise just return the number of pairs in the rest of the string
	}
	
	//	Problem #8
	//	Given a string, return recursively a "cleaned" string where 
	//	adjacent chars that are the same have been reduced 
	//	to a single char. So "yyzzza" yields "yza".  

	//	reduceChars("yyzzza") → "yza"
	//	reduceChars("abbbcdd") → "abcd"
	//	reduceChars("Hello") → "Helo"
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		String with all repeated, adjacent chars are removed
	 */
	static String reduceChars(String str) 
	{
	    if (str.length() < 2) //base case
	        return str;
	    
	    if (str.charAt(0) == str.charAt(1)) //if the current char is equal to the next char
	        return reduceChars(str.substring(1)); //ignore the current char and move down the string
	    
	    return str.substring(0, 1) + reduceChars(str.substring(1)); //if they aren't equal then the current char is not part of a set and is saved
	}
	
	//	Problem #9
	//	Given a string, return true if it is a nesting of zero or more 
	//	pairs of parenthesis, like "(())" or "((()))". Suggestion: 
	//	check the first and last chars, and then recur on what's inside them.  

	//	nestedParens("(())") → true
	//	nestedParens("((()))") → true
	//	nestedParens("(((x))") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing the original chars
	 * 
	 * @return 
	 * 		returns true if there are zero or more pairs of parenthesis
	 * 		returns false if there are not zero or more pairs of parenthesis
	 */
	static boolean nestedParens(String str) 
	{
	    if (str.length() == 0) //first base case: this is a nesting of 0 parentheses
	        return true;
	    if (str.length() < 2) //second base case: there cant be a pair if there are less than 2 characters left
	        return false;

        System.out.println(str.charAt(0) + " " + str.charAt(str.length() - 1) + " - " + str.substring(1, str.length() - 1));
        
	    if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') { //if the first and last char are a pair of parenthesis
	        if (str.length() == 2)
	            return true;
	        return nestedParens(str.substring(1, str.length() - 1)); //test inside the parenthesis
	    }
	    
	    return false; //if the first and last arent a matching pair then the function is false
	}
	
	//	Problem #10

	//	Given a string and a non-empty substring sub, compute 
	//	recursively the largest substring which starts and 
	//	ends with sub and return its length.  

	//	subStrSub("catcowcat", "cat") → 9
	//	subStrSub("catcowcat", "cow") → 3
	//	subStrSub("cccatcowcatxx", "cat") → 9
	
	/**
	 * 
	 * @param str, sub
	 * 		String containing the original chars to be tested against
	 * 		String containing the original chars to test with
	 * 
	 * @return 
	 * 		integer containing the largest number of chars in string 
	 * 		that start and end with sub
	 */
	static int subStrSub(String str, String sub) 
	{
	    if (str.length() < sub.length()) //base case
	        return 0;
	    
	    if (str.substring(0, sub.length()).equals(sub)) { //if we have reached an instance of the substring
	        int lookForward = str.indexOf(sub, 1); //look for the next instance of sub past the one we just found
	        if (lookForward == -1) //if there isnt one
	            return sub.length(); //return the length of sub, as this is the last instance
	        return lookForward + subStrSub(str.substring(lookForward), sub); //otherwise, add the distance from the current instance and move to the next instance
	    }
	    
	    return subStrSub(str.substring(1), sub); //skip over preceding non-sub characters
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
	    
	    
        //Personal Tests
        
//      System.out.println(subCopies("catcowcat", "cat", 2)); // true
//      System.out.println(subCopies("catcowcat", "cow", 2)); // false
//      System.out.println(subCopies("catcowcat", "cow", 1)); // true
//
//      System.out.println(sumDigitsInNumber(126)); // 9
//      System.out.println(sumDigitsInNumber(49)); // 13
//      System.out.println(sumDigitsInNumber(12)); // 3
//
//      System.out.println(exponential(3, 1)); // 3
//      System.out.println(exponential(3, 2)); // 9
//      System.out.println(exponential(3, 3)); // 27
//
//      System.out.println(changeXtoY("codex")); // "codey"
//      System.out.println(changeXtoY("xxhixx")); // "yyhiyy"
//      System.out.println(changeXtoY("xhixhix")); // "yhiyhiy"
//
//      System.out.println(find6(new int[] {1, 6, 4}, 0)); // true
//      System.out.println(find6(new int[] {1, 4}, 0)); // false
//      System.out.println(find6(new int[] {6}, 0)); // true  
//
//      System.out.println(insertAsterisk("hello")); // "h*e*l*l*o"
//      System.out.println(insertAsterisk("abc")); // "a*b*c"
//      System.out.println(insertAsterisk("ab")); // "a*b"
//
//      System.out.println(numberPairs("axa")); // 1
//      System.out.println(numberPairs("axax")); // 2
//      System.out.println(numberPairs("axbx")); // 1
//
//      System.out.println(reduceChars("yyzzza")); // "yza"
//      System.out.println(reduceChars("abbbcdd")); // "abcd"
//      System.out.println(reduceChars("Hello")); // "Helo"
//
//      System.out.println(nestedParens("(())")); // true
//      System.out.println(nestedParens("((()))")); // true
//      System.out.println(nestedParens("(((x))")); // false
//
//      System.out.println(subStrSub("catcowcat", "cat")); // 9
//      System.out.println(subStrSub("catcowcat", "cow")); // 3
//      System.out.println(subStrSub("cccatcowcatxx", "cat")); // 9
	    
	 }

}