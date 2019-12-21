
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek5 
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
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) → true
	//	groupSumsTarget(0, {2, 4, 8}, 14) → true
	//	groupSumsTarget(0, {2, 4, 8}, 9) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{
	    if (start < 0 || start > nums.length - 1 || target <= 0) { //base cases
            if (target == 0)
                return true;
            return false;
        }
	    
	    if (nums[start] > target) { //too big to get target
	        //return false; //no numbers after this number will be smaller than it since it is sorted
	        return groupSumsTarget(start + 1, nums, target); //not sure if array is always sorted, just skip number and continue
	    }
	    else if (nums[start] < target) { //next number can fit into target
	        return groupSumsTarget(start + 1, nums, target) || //try skipping the number
	                groupSumsTarget(start + 1, nums, target - nums[start]); //try using the number
	    }
	    else { //target = nums[start]
	        return true;
	    }
	}

	//	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) → true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) → false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{
	    if (start < 0 || start > nums.length - 1 || target < 0) { //base cases
	        if (target == 0)
	            return true;
            return false;
	    }
       
        if (start == nums.length - 1) //last index, the only time we can know that it is true or not
        {
            if (nums[start] == 6) { //if the last num is a 6
                if (target == 6) //must be used
                    return true;
                return false;
            }
           
            if (target == 0 || target == nums[start])
                return true;
            return false;
        }
       
        if (nums[start] == 6) {
            return groupSumsTarget6(start + 1, nums, target - 6); //must select all 6s even if its too big, if it doesnt fit it will return false in the next call
        }
       
        if (nums[start] > target) { //too big for target
            return groupSumsTarget6(start + 1, nums, target); //skip this number
        }
        else { //number could potentially be used
           return groupSumsTarget6(start + 1, nums, target) || //skip the number
                    groupSumsTarget6(start + 1, nums, target - nums[start]); //use the number
        }
	}	

	//	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
        if (start < 0 || start > nums.length - 1 || target <= 0) { //base cases
            if (target == 0)
                return true;
            return false;
        }
        
        if (nums[start] > target) { //too big to get target
            return groupSumsTargetNoAdj(start + 1, nums, target); //skip number and continue
        }
        else if (nums[start] < target) { //next number can fit into target
            return groupSumsTargetNoAdj(start + 1, nums, target) || //try skipping the number
                    groupSumsTargetNoAdj(start + 2, nums, target - nums[start]); //try using the number, skip the next index if you use the number
        }
        else { //target = nums[start]
            return true;
        }
	}

	//	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
       if (start < 0 || start > nums.length - 1 || target < 0) { //base cases
            if (target == 0)
                return true;
            return false;
        }
       
       if (nums[start] == 1 && start > 0) { //if number is a 1
           if (nums[start - 1] % 5 == 0) //if the previous number was a factor of 5 then it cant be used
               return groupSumsTarget5(start + 1, nums, target); //skip this number
       }
       //otherwise, operate as usual
       
       if (start == nums.length - 1) //last index, the only time we can know that it is true or not
       {
           if (nums[start] % 5 == 0) { //if the last num is a factor of 5
               if (target == nums[start]) //must be used
                   return true;
               return false;
           }
          
           if (target == 0 || target == nums[start])
               return true;
               
           return false;
       }
       
       if (nums[start] % 5 == 0) { //if number is a factor of 5
           return groupSumsTarget5(start + 1, nums, target - nums[start]); //must select all multiples of 5 even if its too big, if it doesnt fit it will return false in the next call
       }
       
       if (nums[start] > target) { //too big for target
           return groupSumsTarget5(start + 1, nums, target); //skip this number
       }
       else { //number could potentially be used
          return groupSumsTarget5(start + 1, nums, target) || //skip the number
                  groupSumsTarget5(start + 1, nums, target - nums[start]); //use the number
       }
	}
	
	//	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) → true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) → true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) → false	
	
	/**
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
        if (start < 0 || start > nums.length - 1 || target <= 0) { //base cases
            if (target == 0)
                return true;
            return false;
        }
        
        int copies = 0; //the number of sequential copies of this number
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == nums[start])
                copies++;
            else {
                break;
            }
        }
        
        int numTotal = nums[start] * copies; //the total value of the entire group
        
        if (numTotal > target) { //too big to get target
            return groupSumsTargetClump(start + copies, nums, target); //skip this group
        }
        else if (numTotal < target) { //next number group can fit into target
            return groupSumsTargetClump(start + copies, nums, target) || //try skipping the number group
                    groupSumsTargetClump(start + copies, nums, target - numTotal); //try using the number group
        }
        else { //target = nums[start]
            return true;
        }
	}
	
	//	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.)    

	//	divideArray({2, 2}) → true
	//	divideArray({2, 3}) → false
	//	divideArray({5, 2, 3}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divideArray(int[] nums) 
	{
	    return divideArray(nums, 0, 0, 0);
	}
	
	static boolean divideArray(int[] nums, int start, int group1, int group2)
	{
	    if (start < 0) //base cases
	        return false;
	    
	    if (start > nums.length - 1) { //all numbers have been allocated
	        if (group1 == group2) //if the two groups are the same size
	            return true;
	        return false;
	    }
	    
	    return divideArray(nums, start + 1, group1 + nums[start], group2) || //try putting the number in the first group
	            divideArray(nums, start + 1, group1, group2 + nums[start]); //try putting the number in the second group
	}
	
	//	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	static boolean oddDivide10(int[] nums) 
	{
        return oddDivide10(nums, 0, 0, 0);
    }
    
    static boolean oddDivide10(int[] nums, int start, int group1, int group2)
    {
        if (start < 0) //base cases
            return false;
        
        if (start > nums.length - 1) { //all numbers have been allocated
            if ((group1 % 10 == 0 && group2 % 2 == 1) || //if group1 is a multiple of 10 and group2 is odd
                (group2 % 10 == 0 && group1 % 2 == 1)) //if group2 is a multiple of 10 and group1 is odd
                return true;
            return false;
        }
        
        return oddDivide10(nums, start + 1, group1 + nums[start], group2) || //try putting the number in the first group
                oddDivide10(nums, start + 1, group1, group2 + nums[start]); //try putting the number in the second group
    }
	
	//	Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) → true
	//	divide53({1, 1, 1}) → false
	//	divide53({2, 4, 2}) → true
	
	/**
	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
        return divide53(nums, 0, 0, 0, true) || divide53(nums, 0, 0, 0, false);
    }
    
    static boolean divide53(int[] nums, int start, int group1, int group2, boolean grouping)
    {
        if (start < 0) //base cases
            return false;
        
        if (start > nums.length - 1) { //all numbers have been allocated
            if (group1 == group2) //if the two groups are the same size
                return true;
            return false;
        }
        
        if (grouping) { //if this is true that means all multiples of 5 go in group 1
            if (nums[start] % 5 == 0) // if number is a multiple of 5
                return divide53(nums, start + 1, group1 + nums[start], group2, grouping);
            else if (nums[start] % 3 == 0) //if number is a multiple of 3 and not a multiple of 5
                return divide53(nums, start + 1, group1, group2 + nums[start], grouping);
        }
        else { //otherwise all multiples of 5 go in group 2
            if (nums[start] % 5 == 0) // if number is a multiple of 5
                return divide53(nums, start + 1, group1, group2 + nums[start], grouping);
            else if (nums[start] % 3 == 0) //if number is a multiple of 3 and not a multiple of 5
                return divide53(nums, start + 1, group1 + nums[start], group2, grouping);
        }
        
        //if the number isnt a multiple of 5 or 3 then
        return divide53(nums, start + 1, group1 + nums[start], group2, grouping) || //try putting the number in the first group
                divide53(nums, start + 1, group1, group2 + nums[start], grouping); //try putting the number in the second group
    }

	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{

	    //Personal Tests
	    
//	    System.out.println(groupSumsTarget(0, new int[] {2, 4, 8}, 10)); // true
//	    System.out.println(groupSumsTarget(0, new int[] {2, 4, 8}, 14)); // true
//	    System.out.println(groupSumsTarget(0, new int[] {2, 4, 8}, 9)); // false
//	    
//	    System.out.println(groupSumsTarget6(0, new int[] {5, 6, 2}, 8)); // true
//	    System.out.println(groupSumsTarget6(0, new int[] {5, 6, 2}, 9)); // false
//	    System.out.println(groupSumsTarget6(0, new int[] {5, 6, 2}, 7)); // false
//	    
//	    System.out.println(groupSumsTargetNoAdj(0, new int[] {2, 5, 10, 4}, 12)); // true
//	    System.out.println(groupSumsTargetNoAdj(0, new int[] {2, 5, 10, 4}, 14)); // false
//	    System.out.println(groupSumsTargetNoAdj(0, new int[] {2, 5, 10, 4}, 7)); // false
//	    
//	    System.out.println(groupSumsTarget5(0, new int[] {2, 5, 10, 4}, 19)); // true
//	    System.out.println(groupSumsTarget5(0, new int[] {2, 5, 10, 4}, 17)); // true
//	    System.out.println(groupSumsTarget5(0, new int[] {2, 5, 10, 4}, 12)); // false
//	    
//	    System.out.println(groupSumsTargetClump(0, new int[] {2, 4, 8}, 10)); // true
//	    System.out.println(groupSumsTargetClump(0, new int[] {1, 2, 4, 8, 1}, 14)); // true
//	    System.out.println(groupSumsTargetClump(0, new int[] {2, 4, 4, 8}, 14)); // false   
//	    
//	    System.out.println(divideArray(new int[] {2, 2})); // true
//	    System.out.println(divideArray(new int[] {2, 3})); // false
//	    System.out.println(divideArray(new int[] {5, 2, 3})); // true
//	    
//	    System.out.println(oddDivide10(new int[] {5, 5, 5})); // true
//	    System.out.println(oddDivide10(new int[] {5, 5, 6})); // false
//	    System.out.println(oddDivide10(new int[] {5, 5, 6, 1})); // true
//	    
//	    System.out.println(divide53(new int[] {1,1})); // true
//	    System.out.println(divide53(new int[] {1, 1, 1})); // false
//	    System.out.println(divide53(new int[] {2, 4, 2})); // true
	}
	
}
