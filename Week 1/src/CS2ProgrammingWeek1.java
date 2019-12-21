/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Assignment 1
 * 20 January 2016
 */

import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek1
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
        return ("Gill,Zachary,3487163");
    }

    // Directions: Return the number of even ints in the given
    // array (The number '0' counts as an even number).
    // Note: the % "mod" operator computes the remainder,
    // e.g. 5 % 2 is 1.

    // CountEvenNumbersInArray({2, 1, 2, 3, 4}) → 3
    // CountEvenNumbersInArray({2, 2, 0}) → 3
    // CountEvenNumbersInArray({1, 3, 5}) → 0

    /**
     *
     * @param NumberList
     *            int[] list containing some numbers.
     *
     * @return int with the number of even numbers in NumberList
     */
    static int CountEvenNumbersInArray(int[] NumberList)
    {
        int countEven = 0; // the counter for even numbers in the list
        for (int i = 0; i < NumberList.length; i++) { // iterate through each element of the array
            if (NumberList[i] % 2 == 0) // if the element is even
                countEven++; // icremement counter
        }
        return countEven;
    }

    // Given an array of ints, return true if the array contains no
    // 1's and no 3's.

    // LookForLucky13({0, 2, 4}) → true
    // LookForLucky13({1, 2, 3}) → false
    // LookForLucky13({1, 2, 4}) → false

    /**
     *
     * @param NumberList
     *            int[] list containing some numbers.
     *
     * @return returns false if there is a 1 or 3 in the list. returns true if there are no 1s or 3s in the list.
     */
    static boolean LookForLucky13(int[] NumberList)
    {
        boolean hit = false; // flag for catching 1's or 3's
        for (int i = 0; i < NumberList.length; i++) { // iterate through each element of the array
            if (NumberList[i] == 1 || NumberList[i] == 3) // if the element is a 1 or 3
                hit = true; // set flag
        }
        return !hit;
    }

    // Given arrays NumberList1 and NumberList2 of the same length,
    // for every element in NumberList1, consider the
    // corresponding element in NumberList2 (at the same index).
    // Return the count of the number of times that the two
    // elements differ by 2 or less, but are not equal.

    // MatchUpLists({1, 2, 3}, {2, 3, 10}) → 2
    // MatchUpLists({1, 2, 3}, {2, 3, 5}) → 3
    // MatchUpLists({1, 2, 3}, {2, 3, 3}) → 2

    static int MatchUpLists(int[] NumberList1, int[] NumberList2)
    {
        int count = 0; // the counter for matching numbers in the lists
        for (int i = 0; i < NumberList1.length; i++) { // iterate through each element of the array
            int difference = NumberList1[i] - NumberList2[i]; // calculate the difference between the two elements
            if (Math.abs(difference) < 3 && difference != 0) // if the difference is 2 or less but not 0
                count++; // icremement counter
        }
        return count;
    }

    // Given an array of ints, return true if the array
    // contains either 3 even or 3 odd values all next
    // to each other.

    // ModThreeNumbers({2, 1, 3, 5}) → true
    // ModThreeNumbers({2, 1, 2, 5}) → false
    // ModThreeNumbers({2, 4, 2, 5}) → true

    /**
     *
     * @param NumberList
     *            int[] list containing some numbers.
     *
     * @return return true if there are three consecutive events or three consecutive odds
     *
     *         otherwise returns false
     */
    public static boolean ModThreeNumbers(int[] NumberList)
    {
        int count = 0; // counter for consecutive even or odd numbers
        boolean last = true; // stores the last number's type, true=even, false=odd

        for (int i = 0; i < NumberList.length; i++) { // iterate through each element of the array
            boolean current = NumberList[i] % 2 == 0;

            if (last) { // if the last number was even
                if (current) // if the current number is even
                    count++; // icremement counter
                else
                    count = 1; // now there is 1 odd number in a row
            } else { // if the last number is odd
                if (!current) // if the current number is odd
                    count++; // incremement counter
                else
                    count = 1; // now there is 1 even number in a row
            }

            if (count == 3) // if the counter ever gets to 3, return true
                return true;

            last = current; // set the last type from the current type
        }

        return false;
    }

    // Return the "centered" average of an array of ints,
    // which we'll say is the mean average of the values,
    // except ignoring the largest and smallest values in
    // the array. If there are multiple copies of the
    // smallest value, ignore just one copy, and likewise
    // for the largest value. Use int division to produce
    // the final average. You may assume that the array is
    // length 3 or more.

    // FindCenteredAverage({1, 2, 3, 4, 100}) → 3
    // FindCenteredAverage({1, 1, 5, 5, 10, 8, 7}) → 5
    // FindCenteredAverage({-10, -4, -2, -4, -2, 0}) → -3

    /**
     *
     * @param NumberList
     *            int[] list containing some numbers.
     *
     * @return Average of the list of numbers without the first of the lowest numbers and the last of the highest numbers.
     */
    static int FindCenteredAverage(int[] NumberList)
    {
        int smallest = Integer.MAX_VALUE; // stores the smallest element
        int largest = Integer.MIN_VALUE; // stores the largest element

        int sum = 0; // stores the sum of all elements

        for (int i = 0; i < NumberList.length; i++) { // iterate through all elements in the array
            sum += NumberList[i]; // add element to sum

            if (NumberList[i] < smallest) // test for smallest number
                smallest = NumberList[i];
            if (NumberList[i] > largest) // test for largest number
                largest = NumberList[i];
        }

        sum -= smallest; // subtract smallest element from sum
        sum -= largest; // subtract largest number from sum

        return sum / (NumberList.length - 2); // return the centered average

    }

    // Given an array of ints, return true if every 2 that
    // appears in the array is next to another 2.

    // LookForTwoTwo({4, 2, 2, 3}) → true
    // LookForTwoTwo({2, 2, 4}) → true
    // LookForTwoTwo({2, 2, 4, 2}) → false

    /**
     *
     * @param NumberList
     *            int[] list containing some numbers.
     *
     * @return true if every 2 is adjacent to another 2 otherwise false
     */
    static boolean LookForTwoTwo(int[] NumberList)
    {
        for (int i = 0; i < NumberList.length; i++) { // incrememnet through elements in the array
            if (NumberList[i] == 2) { // if a 2 is found
                boolean match = false; // flag for if the two has another two next to it

                if (i > 0) // ensure index in bounds
                    if (NumberList[i - 1] == 2) // if the index before it is a 2 also then its good
                        match = true;

                if (i < NumberList.length - 2) // ensure index in bounds
                    if (NumberList[i + 1] == 2) // if the index after it is a 2 also then its good
                        match = true;

                if (!match) // if there is a 2 by itself
                    return false;
            }
        }

        return true;
    }

    ///////////////////////////////////////////
    //
    // End of assignment code.
    //
    ///////////////////////////////////////////

    public static void main(String[] args)
    {


        
        //Personal Tests:
        
//        System.out.println(CountEvenNumbersInArray(new int[] {2, 1, 2, 3, 4})); // 3
//        System.out.println(CountEvenNumbersInArray(new int[] {2, 2, 0})); // 3
//        System.out.println(CountEvenNumbersInArray(new int[] {1, 3, 5})); // 0
//
//
//        System.out.println(LookForLucky13(new int[] {0, 2, 4})); // true
//        System.out.println(LookForLucky13(new int[] {1, 2, 3})); // false
//        System.out.println(LookForLucky13(new int[] {1, 2, 4})); // false
//
//
//        System.out.println(MatchUpLists(new int[] {1, 2, 3}, new int[] {2, 3, 10})); // 2
//        System.out.println(MatchUpLists(new int[] {1, 2, 3}, new int[] {2, 3, 5})); // 3
//        System.out.println(MatchUpLists(new int[] {1, 2, 3}, new int[] {2, 3, 3})); // 2
//
//
//        System.out.println(ModThreeNumbers(new int[] {2, 1, 3, 5})); // true
//        System.out.println(ModThreeNumbers(new int[] {2, 1, 2, 5})); // false
//        System.out.println(ModThreeNumbers(new int[] {2, 4, 2, 5})); // true
//
//
//        System.out.println(FindCenteredAverage(new int[] {1, 2, 3, 4, 100})); // 3
//        System.out.println(FindCenteredAverage(new int[] {1, 1, 5, 5, 10, 8, 7})); // 5
//        System.out.println(FindCenteredAverage(new int[] {-10, -4, -2, -4, -2, 0})); // -3
//
//
//        System.out.println(LookForTwoTwo(new int[] {4, 2, 2, 3})); // true
//        System.out.println(LookForTwoTwo(new int[] {2, 2, 4})); // true
//        System.out.println(LookForTwoTwo(new int[] {2, 2, 4, 2})); // false
    }

}
