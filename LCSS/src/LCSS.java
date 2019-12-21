import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * LCSS
 * 7 April 2016
 */

/**
 * @author Zachary Gill
 *
 */
public class LCSS
{
    public static int[][] memo; //memoization
    public static boolean[][] bridges;
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in); //read input from console
        
        int n = Integer.decode(scanner.nextLine()); //number of test cases
        
        for (int i = 0; i < n; i++) {
            //Read input
            String s = scanner.nextLine(); //first string
            String t = scanner.nextLine(); //second string
            
            List<String> ls = tokenize(s);
            List<String> lt = tokenize(t);
            
            //Calulcate least common subsequence
            List<String> lcss = computeLCSS(ls, lt);
            
            //Print output
            System.out.print("LCSS Length = " + lcss.size() + ". LCSS =");
            for (int j = 0; j < lcss.size(); j++)
                System.out.print(" " + lcss.get(j));
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * Computes the LCSS between two lists of strings iteratively.
     * 
     * @param s : The first list of strings.
     * @param t : The second list of strings.
     */
    private static List<String> computeLCSS(List<String> s, List<String> t)
    {
        renewMemo(s.size() + 1, t.size() + 1);

        for (int i = 1; i <= s.size(); i++) { //for each row
            for (int j = 1; j <= t.size(); j++) { //for each column
                
                if (s.get(i - 1).equals(t.get(j - 1))) { //if the two are equal
                    memo[i][j] = memo[i - 1][j - 1] + 1; //take the upper left corner plus 1
                    bridges[i][j] = true;
                }
                else {
                    memo[i][j] = Math.max(
                            memo[i - 1][j],
                            memo[i][j - 1]);
                    bridges[i][j] = false;
                }
            }
        }
                
        return determineLCSS(s, t);
    }
    
    /**
     * Determines the LCSS from the memo data.
     * 
     * @param s : The first list of strings.
     * @param t : The second list of strings.
     * @return The LCSS as a list.
     */
    private static List<String> determineLCSS(List<String> s, List<String> t)
    {
        LinkedList<String> lcss = new LinkedList<String>();
        
        int i = memo.length - 1;
        int j = memo[0].length - 1;
        
        while (memo[i][j] > 0 && i > 0 && j > 0) {
            if (bridges[i][j]) {
                lcss.push(s.get(i - 1)); //add word to lcss
                i--;
                j--;
            }
            else {
                if (memo[i - 1][j] == memo[i][j]) //preference to move up instead of left
                    i--;
                else
                    j--;
            }
        }
        
        return lcss;
    }
    
    /**
     * Resizes and presets the memo and bridges arrays.
     * 
     * @param x : The x dimension of the array.
     * @param y : The y dimension of the array.
     */
    private static void renewMemo(int x, int y)
    {
        memo = new int[x][y]; //first string goes along rows, second string goes along columns
        bridges = new boolean[x][y];
        
        //preset first row and column of memo to 0
        for (int i = 0; i < x; i++)
            memo[i][0] = 0;
        for (int i = 0; i < y; i++)
            memo[0][i] = 0;
    }
    
    /**
     * Prints the contents of the memo.
     */
    public static void printMemo()
    {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the contents of bridges.
     */
    public static void printBridges()
    {
        for (int i = 0; i < bridges.length; i++) {
            for (int j = 0; j < bridges[0].length; j++) {
                System.out.print((bridges[i][j] ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Tokenizes a passed string into its words and returns a list of those words.
     *
     * @param str : The string to tokenize.
     * @return A list of all the tokens of the passed string.
     */
    public static List<String> tokenize(String str) {
        List<String> tokens = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens())
            tokens.add(st.nextToken());

        return tokens;
    }

}
