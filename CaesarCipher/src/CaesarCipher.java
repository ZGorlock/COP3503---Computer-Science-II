/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Caesar Cipher
 * 21 March 2016
 */

import java.util.Arrays;


/**
 * @author Zachary Gill
 *
 */
public class CaesarCipher
{
    
    public static final int NUM_LETTERS = 26;
    
    static double[] table = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
    
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {   
        System.out.println("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!");
        System.out.println(crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
    }

    /**
     * Converts a char to its natural number representation.
     * @param c : The char.
     * @return The natural number representation of the char.
     */
    public static int let2nat(char c)
    {
        return c - 'a';
    }
    
    /**
     * Converts a natural number representation of a char to a char.
     * @param code : The natural number representation.
     * @return The char it represents.
     */
    public static char nat2let(int code)
    {
        return (char)(code + 'a');
    }
    
    /**
     * Shifts a char by the amount specified.
     * @param shftAmt : The amount to shift.
     * @param c : The char.
     * @return The char after the shift.
     */
    public static char shift(int shftAmt, char c)
    {
        if (c >= 'a' && c <= 'z')
            return nat2let((let2nat(c) + shftAmt) % NUM_LETTERS);
        
        return c;
    }
    
    /**
     * Encodes a string using a caesar shift.
     * @param shftAmnt : The amount to shift.
     * @param str : The string to encode.
     * @return The encoded string.
     */
    public static String encode(int shftAmnt, String str)
    {
        String newStr = "";
        
        for (int i = 0; i < str.length(); i++)
            newStr += shift(shftAmnt, str.charAt(i));
        
        return newStr;
    }

    /**
     * Decodes a string using a caesar shift.
     * @param shftAmnt : The amount to reverse shift.
     * @param str : The string to decode.
     * @return The decoded string.
     */
    public static String decode(int shftAmnt, String str)
    {
        String newStr = "";
        
        for (int i = 0; i < str.length(); i++)
            newStr += shift((NUM_LETTERS - shftAmnt), str.charAt(i));
        
        return newStr;
    }
    
    /**
     * Determines if a char is lower-case.
     * @param c : The char.
     * @return Whether the char is lower-case or not.
     */
    public static boolean isLower(char c)
    {
        return (c >= 'a' && c <= 'z');
    }
    
    /**
     * Calculates the number of lower-case letters in a string.
     * @param str : The string.
     * @return The number of lower-case letters the string contains.
     */
    public static int lowers(String str)
    {
        if (str.length() == 0)
            return 0;
        
        if (isLower(str.charAt(0)))
            return 1 + lowers(str.substring(1));
        
        return lowers(str.substring(1));
    }
    
    /**
     * Calculates the number of a given character in a string.
     * @param c : The char to look for.
     * @param str : The string.
     * @return The number of instances of the char in the string.
     */
    public static int count(char c, String str)
    {
        if (str.length() == 0)
            return 0;
        
        if (str.charAt(0) == c)
            return 1 + count(c, str.substring(1));
        
        return count(c, str.substring(1));
    }
    
    /**
     * Calculates the percentage of one integer with respect to another.
     * @param num1 : The first integer.
     * @param num2 : The second integer.
     * @return The percentage of the first integer with repect to the second.
     */
    public static double percent(int num1, int num2)
    {
        return 100 * ((double)num1 / num2);
    }
    
    /**
     * Calculates the percentage frequencies of each lower-case letter in the string.
     * @param str : The string.
     * @return The frequecy percentages.
     */
    public static double[] freqs(String str)
    {
        double freq[] = new double[NUM_LETTERS];
        
        for (int i = 0; i < NUM_LETTERS; i++)
            freq[i] = percent(count(nat2let(i), str), str.length());
        
        return freq;
    }
    
    /**
     * Rotates a list to left by a specified amount.
     * @param n : The shift amount.
     * @param list : The list to shift.
     * @return The rotated list.
     */
    public static double[] rotate(int n, double[] list)
    {
        double rotates[] = new double[list.length];
        
        for (int i = 0; i < list.length; i++) {
            int newIndex = i - n;
            while (newIndex < 0)
                newIndex += list.length;
            rotates[newIndex] = list[i];
        }
        
        return rotates;
    }
    
    /**
     * Calculates the chi square for an observed frequency list with respect to the static frequencies.
     * @param os : The observed frequency list.
     * @return The chi square of the observed frequency list.
     */
    public static double chisqr(double[] os)
    {
        if (os.length != table.length)
            return 0;
        
        double sum = 0.0;
                    
        for (int i = 0; i < os.length; i++)
            sum += (Math.pow(os[i] - table[i], 2) / table[i]);
        
        return sum;
    }
    
    /**
     * Finds the position of the first instance of a value in an array.
     * @param a : The value to look for.
     * @param list : The array to search in.
     * @return The position of the first instance of the value.
     *          -1 if the value does not exist.
     */
    public static int position(double a, double[] list)
    {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == a)
                return i;
        }
        
        return -1;
    }
    
    /**
     * Attempts to crack a caesar cipher.
     * @param str : The encoded string to crack.
     * @return The decoded string.
     */
    public static String crack(String str)
    {
        double minChiSqr = Integer.MAX_VALUE;
        int shftAmnt = 0;
        
        double freq[] = freqs(str);
        
        for (int i = 0; i < NUM_LETTERS - 1; i++) {
            double chiSqr = chisqr(rotate(i, freq));
            
            if (chiSqr < minChiSqr) {
                minChiSqr = chiSqr;
                shftAmnt = i;
            }
        }
        
        return decode(shftAmnt, str);
    }
    
}
