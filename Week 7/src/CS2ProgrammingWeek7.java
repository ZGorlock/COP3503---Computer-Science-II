import java.util.Arrays;
import java.util.Scanner;


///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek7
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
		return("Gill,Zachary,3487163");
	}
	
	//	Problem #1
	//	We want to make a row of bricks that is goal inches long. We have a number of 
	//	small bricks (1 inch each) and big bricks (5 inches each). Return true if it 
	//	is possible to make the goal by choosing from the given bricks. This is a 
	//	little harder than it looks and can be done without any loops.

	//	makeRowOfGoalBricks(3, 1, 8) → true
	//	makeRowOfGoalBricks(3, 1, 9) → false
	//	makeRowOfGoalBricks(3, 2, 10) → true
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1inch bricks available
	 * 		int containing the number of 5inch bricks available
	 * 		int containing the number of inches for the goal
	 * 
	 * @return 
	 * 		returns true if the goal can be reached with the available bricks
	 * 		returns false if the goal cannot be reached with the available bricks
	 */
	static boolean makeRowOfGoalBricks(int small, int big, int goal) 
	{
	    int bricksLeft = goal;
	    int fitLargeBricks = goal / 5; //see how many large bricks would fit
	    
	    //use as many large bricks as you can
	    if (fitLargeBricks <= big)
	        bricksLeft -= fitLargeBricks * 5;
	    else
            bricksLeft -= big * 5;
	    
	    //if you have enough small bricks left then return true
	    if (small < bricksLeft)
	        return false;
	    return true;
	}

	//	Problem #2
	//	Given 3 int values, a b c, return their sum. However, if one of the values 
	//	is the same as another of the values, it does not count towards the sum.

	//	sumExcludingDuplicates(1, 2, 3) → 6
	//	sumExcludingDuplicates(3, 2, 3) → 2
	//	sumExcludingDuplicates(3, 3, 3) → 0
	
	/** 
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where duplicates are not included
	 */
	static int sumExcludingDuplicates(int a, int b, int c) 
	{
	    int sum = 0;
	    
	    //if the value does not equal either of the other 2 values, then add it to the sum
	    if (a != b && a != c)
	        sum += a;
	    if (b != a && b != c)
	        sum += b;
	    if (c != a && c != b)
	        sum += c;
	    
	    return sum;
	}	

	//	Problem #3
	//	Given 3 int values, a b c, return their sum. However, if one of the values is 
	//	13 then it does not count towards the sum and values to its right do not 
	//	count. So for example, if b is 13, then both b and c do not count. 

	//	sumExcludingUnluckyNums(1, 2, 3) → 6
	//	sumExcludingUnluckyNums(1, 2, 13) → 3
	//	sumExcludingUnluckyNums(1, 13, 3) → 1
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where values to the right of 13, inclusive, are not included
	 */
	static int sumExcludingUnluckyNums(int a, int b, int c) 
	{
	    int sum = 0;
	    
	    //cant add anything else if you hit a 13
	    if (a != 13) { //as long as a is not 13
	        sum += a;
	        
	        if (b != 13) { //as long as b is not 13
	            sum += b;
	            
	            if (c != 13) //as long as c is not 13
	                sum += c;
	        }
	    }
	    
	    return sum;
	}	

	//	Problem #4
	//	Given 3 int values, a b c, return their sum. However, if any of the values is a 
	//	teen -- in the range 13..19 inclusive -- then that value counts as 0, except 15 
	//	and 16 do not count as teens. Write a separate helper "public int fixTeen(int n) 
	//	{"that takes in an int value and returns that value fixed for the teen rule. In 
	//	this way, you avoid repeating the teen code 3 times (i.e. "decomposition").

	//	sumExcludingTeens(1, 2, 3) → 6
	//	sumExcludingTeens(2, 13, 1) → 3
	//	sumExcludingTeens(2, 1, 14) → 3
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where teens are not included
	 */
	static int sumExcludingTeens(int a, int b, int c) 
	{
	    return fixTeen(a) + fixTeen(b) + fixTeen(c);
	}
	
	static int fixTeen(int num)
	{
	    if (num < 13 || num > 19) //not a teen
	        return num;
	    
	    if (num == 15 || num == 16) //not a teen
	        return num;
	    
	    return 0; //a teen
	}
	
	//	Problem #5
	//	For this problem, we'll round an int value up to the next multiple of 10 if its rightmost 
	//	digit is 5 or more, so 15 rounds up to 20. Alternately, round down to the previous multiple 
	//	of 10 if its rightmost digit is less than 5, so 12 rounds down to 10. Given 3 ints, 
	//	a b c, return the sum of their rounded values. To avoid code repetition, write a separate 
	//	helper "public int round10(int num) {" and call it 3 times. Write the helper entirely below 
	//	and at the same indent level as roundSum().

	//	roundedSum(16, 17, 18) → 60
	//	roundedSum(12, 13, 14) → 30
	//	roundedSum(6, 4, 4) → 10
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing the original integers to sum
	 * 
	 * @return
	 * 		returns the sum of the input where each value is rounded to the nearest tens place
	 */
	static int roundedSum(int a, int b, int c) 
	{
	    return round(a) + round(b) + round(c);
	}
	
	static int round(int num)
	{
	    if (num % 10 < 5)
	        return num - (num % 10); //round down
	    
	    return num + (10 - (num % 10)); //round up
	}
	
	//	Problem #6
	//	Given three ints, a b c, return true if one of b or c is "close" (differing from 
	//	a by at most 1), while the other is "far", differing from both other values by 2 
	//	or more. Note: Math.abs(num) computes the absolute value of a number. 

	//	isCloseAndFar(1, 2, 10) → true
	//	isCloseAndFar(1, 2, 3) → false
	//	isCloseAndFar(4, 1, 3) → true
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints with original integers to compute relativity
	 * 
	 * @return 
	 * 		returns true if one of b or c is close to a and if the other is far from both other values
	 */
	static boolean isCloseAndFar(int a, int b, int c) 
	{
	    if ( (Math.abs(a - b) <= 1 && (Math.abs(a - c) >= 2 && Math.abs(b - c) >= 2)) || //if b is close and c is far 
	            (Math.abs(a - c) <= 1 && (Math.abs(a - b) >= 2 && Math.abs(c - b) >= 2)) ) //or c is close and b is far
	        return true;
	    
	    return false;
	}
	
	//	Problem #7
	//	Given 2 int values greater than 0, return whichever value is nearest to 21 without 
	//	going over. Return 0 if they both go over. 

	//	blackjack(19, 21) → 21
	//	blackjack(21, 19) → 21
	//	blackjack(19, 22) → 19
	
	/**
	 * 
	 * @param a, b
	 * 		ints representing the values of two cards in a game of black jack
	 * 
	 * @return 
	 * 		returns the value of the int that is closest to 21 without going over
	 */
	static int blackjack(int a, int b) 
	{
	    if (a > 21 && b > 21) //if they are both over 21
	        return 0;
	    
	    if (a > 21 && b <= 21) //if only b is a viable choice
	        return b;
	    if (b > 21 && a <= 21) //if only a is a viable choice
	        return a;
	    
	    //at this point, both a and b must be less than or equal to 21
	    if (21 - a < 21 - b) //if a is closer to 21 than b
	        return a;
	    
	    return b;
	}
	
	//	Problem #8
	//	Given three ints, a b c, one of them is small, one is medium and one is large. 
	//	Return true if the three values are evenly spaced, so the difference between 
	//	small and medium is the same as the difference between medium and large. 

	//	spacedEvenly(2, 4, 6) → true
	//	spacedEvenly(4, 6, 2) → true
	//	spacedEvenly(4, 6, 3) → false
	
	/**
	 * 
	 * @param a, b, c
	 * 		ints containing original integers to compute with
	 * 
	 * @return 
	 * 		returns true if the input values are evenly spaced
	 * 		returns false if the input values are not evenly spaced
	 */
	static boolean spacedEvenly(int a, int b, int c) 
	{
	    int[] arr = new int[3];
	    arr[0] = a; arr[1] = b; arr[2] = c; //move values into array
	    Arrays.sort(arr); //sort the array
	    
	    if (arr[1] - arr[0] == arr[2] - arr[1]) //test if they are evenly spaced
	        return true;
	    return false;
	}
	
	//	Problem #9
	//	We want to make a package of goal kilos of chocolate. We have small bars 
	//	(1 kilo each) and big bars (5 kilos each). Return the number of small bars 
	//	to use, assuming we always use big bars before small bars. Return -1 
	//	if it can't be done.

	//	makeKilosOfChocolate(4, 1, 9) → 4
	//	makeKilosOfChocolate(4, 1, 10) → -1
	//	makeKilosOfChocolate(4, 1, 7) → 2
	
	/**
	 * 
	 * @param small, big, goal
	 * 		int containing the number of 1kilo bars available
	 * 		int containing the number of 5kilo bars available
	 * 		int containing the number of kilos for the goal
	 * 
	 * @return 
	 * 		returns the value of the number of small bars needed to meet the goal
	 */
	static int makeKilosOfChocolate(int small, int big, int goal) 
	{
        int barsLeft = goal;
        int fitLargeBars = goal / 5; //see how many large bars would fit
        
        //use as many large bars as you can
        if (fitLargeBars <= big)
            barsLeft -= fitLargeBars * 5;
        else
            barsLeft -= big * 5;
        
        //if you have enough small bars left then return that
        if (small >= barsLeft)
            return barsLeft;
        return -1; //otherwise it cant be done
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{
	    
	    
	    //Personal Tests
	    
//	    System.out.println(makeRowOfGoalBricks(3, 1, 8)); // true
//	    System.out.println(makeRowOfGoalBricks(3, 1, 9)); // false
//      System.out.println(makeRowOfGoalBricks(3, 2, 10)); // true
//	    
//	    System.out.println(sumExcludingDuplicates(1, 2, 3)); // 6
//	    System.out.println(sumExcludingDuplicates(3, 2, 3)); // 2
//      System.out.println(sumExcludingDuplicates(3, 3, 3)); // 0
//	    
//	    System.out.println(sumExcludingUnluckyNums(1, 2, 3)); // 6
//	    System.out.println(sumExcludingUnluckyNums(1, 2, 13)); // 3
//      System.out.println(sumExcludingUnluckyNums(1, 13, 3)); // 1
//	    
//	    System.out.println(sumExcludingTeens(1, 2, 3)); // 6
//	    System.out.println(sumExcludingTeens(2, 13, 1)); // 3
//      System.out.println(sumExcludingTeens(2, 1, 14)); // 3
//	    
//	    System.out.println(roundedSum(16, 17, 18)); // 60
//	    System.out.println(roundedSum(12, 13, 14)); // 30
//      System.out.println(roundedSum(6, 4, 4)); // 10
//	    
//	    System.out.println(isCloseAndFar(1, 2, 10)); // true
//	    System.out.println(isCloseAndFar(1, 2, 3)); // false
//      System.out.println(isCloseAndFar(4, 1, 3)); // true
//	    
//	    System.out.println(blackjack(19, 21)); // 21
//	    System.out.println(blackjack(21, 19)); // 21
//      System.out.println(blackjack(19, 22)); // 19
//	    
//	    System.out.println(spacedEvenly(2, 4, 6)); // true
//	    System.out.println(spacedEvenly(4, 6, 2)); // true
//      System.out.println(spacedEvenly(4, 6, 3)); // false
//	    
//	    System.out.println(makeKilosOfChocolate(4, 1, 9)); // 4
//	    System.out.println(makeKilosOfChocolate(4, 1, 10)); // -1
//      System.out.println(makeKilosOfChocolate(4, 1, 7)); // 2
	}
	
}