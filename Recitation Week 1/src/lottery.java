/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Recitation 1
 * 20 January 2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for determining lottery winners.
 * @author Zachary Gill
 */
public class lottery
{
    //Constants

    public static final int NUMBER_COUNT = 6;


    //Data Structures

    /**
     * Data structure to store information about a lottery ticket.
     * @author Zachary Gill
     */
    public class Ticket
    {
        /**
         * Stores the first name on the ticket.
         */
        public String nameFirst;

        /**
         * Stores the last name on the ticket.
         */
        public String nameLast;

        /**
         * Stores the 6 numbers on the ticket.
         */
        public int[] numbers;

        /**
         * Default no-argument constructor.
         */
        public Ticket()
        {
            nameFirst = "";
            nameLast = "";
            numbers = new int[6];
        }

        /**
         * Creates and initializes a new Ticket with the passed data.
         * @param nameFirst : The first name on the ticket.
         * @param nameLast : The last name on the ticket.
         * @param numbers : The numbers on the ticket. Must be in ascending order.
         */
        public Ticket(String nameFirst, String nameLast, int[] numbers)
        {
            this.nameFirst = nameFirst;
            this.nameLast = nameLast;
            this.numbers = numbers;
        }
    }


    //Enums

    /**
     * Enumerated type for possible winning values.
     * @author Zachary Gill
     */
    public enum WinningValues
    {
        THREE_MATCHED (10),
        FOUR_MATCHED  (100),
        FIVE_MATCHED  (10000),
        SIX_MATCHED   (1000000);

        /**
         * Stores the dollar value of a winning value.
         */
        public final int winnings;

        /**
         * Constructor for enumerated winning values.
         */
        private WinningValues(int winnings)
        {
            this.winnings = winnings;
        }
    }


    //Fields

    /**
     * Stores an array of lottery tickets.
     */
    private Ticket[] tickets;

    /**
     * Stores the winning lottery numbers.
     */
    private int[] winningNumbers;


    //Constructors

    /**
     * Default constructor for lottery class.
     */
    public lottery()
    {
        tickets = new Ticket[0];
        winningNumbers = new int[NUMBER_COUNT];
    }

    /**
     * Creates a lottery object and intializes it with data from a file.
     */
    public lottery(String file)
    {
        File scannerFile = new File(file);
        try {
            Scanner inFile = new Scanner(scannerFile);

            int n = 0;
            n = inFile.nextInt(); //get number of tickets stored in the file

            tickets = new Ticket[n]; //dynamically allocate array to hold this many tickets
            for (int i = 0; i < n; i++) { //for each expected ticket
                String nameLast = inFile.next();
                String nameFirst = inFile.next();
                int[] numbers = new int[NUMBER_COUNT];
                
                for (int j = 0; j < NUMBER_COUNT; j++) { //for each number on the lottery ticket
                    numbers[j] = inFile.nextInt();
                }
                tickets[i] = new Ticket(nameFirst, nameLast, numbers); //create new ticket and store in the array
            }

            inFile.close(); //close the input stream

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        winningNumbers = new int[NUMBER_COUNT];

    }


    //Methods

    /**
     * Modifier to set the winning lottery numbers.
     * @param winningNumbers : The and array of the winning lottery numbers.
     */
    private void setWinningNumbers(int[] winningNumbers)
    {
        this.winningNumbers = winningNumbers;
    }

    /**
     * Processes the tickets in the array and print out winners.
     */
    private void processTickets()
    {
        for (int i = 0; i < tickets.length; i++) { //for each ticket in the array
            int n = isWinner(i); //test if it is a winner
            if (n > 2) //if it has 3 or more matching numbers
                outputTicket(i, n); //output the ticket to the screen
        }
    }

    /**
     * Determines if the ticket is a winner.
     * @param index : The index of the ticket to test.
     * @return The number of matching numbers.
     */
    private int isWinner(int index)
    {
        int matchingNumbers = 0; //counter for matching numbers

        int wIndex = 0; //stores the index of the winning numbers
        int tIndex = 0; //stores the index of the ticket's numbers

        int[] numbers = tickets[index].numbers;

        while (wIndex < NUMBER_COUNT && tIndex < NUMBER_COUNT) { //while neither list has reached the end
            if (winningNumbers[wIndex] < numbers[tIndex]) //if current number on winning number list is bigger than current number on ticket
                wIndex++; //move to a higher number on the winning number list
            else if (winningNumbers[wIndex] > numbers[tIndex]) //if current number on the winning number list is less than the current number on the ticket
                tIndex++; //move to a higher number on the ticket
            else {
                matchingNumbers++; //otherwise, the numbers match
                wIndex++;
                tIndex++;
            }
        }

        return matchingNumbers;
    }

    /**
     * Prints a winning ticket to the screen.
     * @param index : The index of the ticket to print.
     * @param matchingNumbers : The number of matching numbers.
     */
    private void outputTicket(int index, int matchingNumbers)
    {
        if (matchingNumbers < 3)
            return;

        System.out.println(tickets[index].nameFirst + " " + tickets[index].nameLast + " " +
                "matched " + matchingNumbers + " numbers " +
                "and won $" + WinningValues.values()[matchingNumbers - 3].winnings);
    }


    //Main method

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in); //user input scanner

        //get input file name
        System.out.println("Enter the name of the input file: ");
        String file = in.next(); //get input file

        //set winning lottery numbers
        int[] winningNumbers = new int[NUMBER_COUNT];
        System.out.println("Enter the winning lottery numbers: "); //must be entered in ascending order
        for (int i = 0; i < NUMBER_COUNT; i++)
            winningNumbers[i] = in.nextInt(); //get the winning lottery numbers

        in.close(); //close input stream


        lottery l = new lottery(file); //create lottery object with input file

        l.setWinningNumbers(winningNumbers); //set winning lottery numbers

        l.processTickets(); //process the lottery ticket to determine winners
    }


}
