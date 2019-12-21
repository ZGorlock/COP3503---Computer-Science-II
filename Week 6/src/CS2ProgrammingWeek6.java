import java.util.Scanner;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
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
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2
	
	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	static int wordEndYZ(String str) 
	{
	    int count = 0;
	    
	    for (int i = 0; i < str.length(); i++) {
	        if (str.charAt(i) == 'y' || str.charAt(i) == 'Y' || 
	                str.charAt(i) == 'z' || str.charAt(i) == 'Z') { //if you find a y or z
	            if (i == str.length() - 1) //end of string
	                count++;
	            else if (!Character.isLetter(str.charAt(i + 1))) //end of word
	                count++;
	        }
	    }
	    
	    return count;
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"
	
	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
	    int index;
	    	    
	    while ((index = base.indexOf(remove)) != -1) { //as long as there is still an instance of remove in base
	        base = base.substring(0, index) + base.substring(index + remove.length()); //remove it
	    }
	    
	    return base;
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true
	
	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
	    int isCount = 0;
	    int notCount = 0;
	    
	    //count appearances of 'is'
	    for (int i = 0; i < str.length(); i++) {
	        i = str.indexOf("is", i);
	        
	        if (i == -1) { //no more instances
	            break;
	        }
	        else {
	            isCount++;
	            i += ("is").length() - 1;
	        }
	    }

        //count appearances of 'not'
        for (int i = 0; i < str.length(); i++) {
            i = str.indexOf("not", i);
            
            if (i == -1) { //no more instances
                break;
            }
            else {
                notCount++;
                i += ("not").length() - 1; //move past last occurance, but not all the way; next loop will increment up
            }
        }
        
        return (isCount == notCount);
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false
	
	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
	    for (int i = 0; i < str.length(); i++) {
	        if (str.charAt(i) == 'g') { //if you found a g
	            if ( !( (i > 0 && str.charAt(i - 1) == 'g') || //if there is a g before
	                    (i < str.length() - 1 && str.charAt(i + 1) == 'g') ) ) //or there is a g after
	                return false; //if not that, return false
	        }
	    }
	    return true; //reached end without returning false
	}
	
	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
	    int count = 0;
	    
	    for (int i = 0; i < str.length() - 2; i++) { //only go to str.length - 3 to avoid index errors
	        if (str.charAt(i + 1) == str.charAt(i) && //if the next char is the same
	                str.charAt(i + 2) == str.charAt(i)) //and the next next char is the same
	            count++; //you found a triple
	    }
	    
	    return count;
	}
	
	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
	    int sum = 0;
	    
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) //if the char is a number
	            sum += Integer.parseInt(Character.toString(str.charAt(i))); //add it to the sum
	    }
	    
	    return sum;
	}
	
	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	static String beginningAndEndOfString(String string) 
	{
	    String cap = "";
	    
	    for (int i = 0; i < string.length() / 2 + 1; i++) { //look through the first half of the string
            String substr1 = string.substring(0, i); //front cap
            String substr2 = string.substring(string.length() - i); //end cap
            
            
            if (substr1.equals(substr2)) //test if they are equal
                cap = substr1; //save current cap
	    }
	    
	    return cap;
	}
	
	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"
	
	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
	    String mirror = "";
	    
	    for (int i = 0; i < string.length(); i++) { //increase mirror size up to string length
	        boolean match = true;
	        
	        for (int j = 0; j <= i; j++) { //for the length of the string
	            if (string.charAt(j) != string.charAt(string.length() - 1 - j)) //if mirror fails
	                match = false;
	        }
	        
	        if (match) //if mirror didnt fail
	            mirror = string.substring(0, i + 1); //save current string
	        
	    }
	        
	    return mirror;
	}
	
	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
	    int maxSize = 0;
	    
	    for (int i = 0; i < str.length(); i++) {
	        int size = 0;
	        
	        for (int j = i; j < str.length(); j++) { //move from current char to end of string
	            if (str.charAt(i) == str.charAt(j)) //as long as the char is still a block
	                size++; //increment size
	            else { //onee block stops
                    break; //leave for loop
                }
	        }
	        
	        if (size > maxSize) //test if new block is the largest so far
	            maxSize = size;
	    }
	    
	    return maxSize;
	}
	
	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18
	
	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
        int sum = 0;
        
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) { //if the char is a number
                int num = Integer.parseInt(str.substring(i, i + 1));
                
                for (int j = i + 1; j < str.length(); j++) {
                    if (Character.isDigit(str.charAt(j))) { //while there is more to the number
                        num = num * 10 + Integer.parseInt(str.substring(j, j + 1)); //determine value of next digit
                        i++; //incremement other index
                    }
                    else {
                        break;
                    }
                }
                
                sum += num; //add it to the sum
            }
        }
        
        return sum;
	}
	
	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////
	
	public static void main(String[] args)
	{

	    
	    //Personal Tests
	    
//	    System.out.println(wordEndYZ("fez day")); // 2
//	    System.out.println(wordEndYZ("day fez")); // 2
//      System.out.println(wordEndYZ("day fyyyz")); // 2
//	    
//	    System.out.println(removeFromBase("Hello there", "llo")); // "He there"
//	    System.out.println(removeFromBase("Hello there", "e")); // "Hllo thr"
//      System.out.println(removeFromBase("Hello there", "x")); // "Hello there"
//	    
//	    System.out.println(equalAppearance("This is not")); // false
//	    System.out.println(equalAppearance("This is notnot")); // true
//      System.out.println(equalAppearance("noisxxnotyynotxisi")); // true
//	    
//	    System.out.println(gisHappy("xxggxx")); // true
//	    System.out.println(gisHappy("xxgxx")); // false
//      System.out.println(gisHappy("xxggyygxx")); // false
//	    
//	    System.out.println(numberOfTriples("abcXXXabc")); // 1
//	    System.out.println(numberOfTriples("xxxabyyyycd")); // 3
//	    System.out.println(numberOfTriples("a")); // 0  
//	    
//	    System.out.println(addUpDigits("aa1bc2d3")); // 6
//	    System.out.println(addUpDigits("aa11b33")); // 8
//      System.out.println(addUpDigits("Chocolate")); // 0
//	    
//	    System.out.println(beginningAndEndOfString("abXYab")); // "ab"
//	    System.out.println(beginningAndEndOfString("xx")); // "x"
//      System.out.println(beginningAndEndOfString("xxx")); // "x"
//	    
//	    System.out.println(beginningMirrorEnd("abXYZba")); // "ab"
//	    System.out.println(beginningMirrorEnd("abca")); // "a"
//	    System.out.println(beginningMirrorEnd("aba")); // "aba"
//	    
//	    System.out.println(largestBlock("hoopla")); // 2
//	    System.out.println(largestBlock("abbCCCddBBBxx")); // 3
//	    System.out.println(largestBlock("")); // 0
//	    
//	    System.out.println(addUpNumbers("abc123xyz")); // 123
//	    System.out.println(addUpNumbers("aa11b33")); // 44
//	    System.out.println(addUpNumbers("7 11")); // 18	    
	}
	
}