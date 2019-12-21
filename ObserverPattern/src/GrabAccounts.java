/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Observer Pattern
 * 11 April 2016
 */

import java.util.Scanner;


/**
 * @author Zachary Gill
 *
 */
public class GrabAccounts
{
    
    private static AccountGrabber subject;
    private static Scanner scanner;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        subject = new AccountGrabber();
        scanner = new Scanner(System.in);
        
        int input = 0;
        do {
            System.out.print("Main Menu\n" + 
                    "1. Create Account\n" + 
                    "2. Toggle SleepMode on an Account\n" + 
                    "3. Edit Youtube Account Info\n" + 
                    "4. Delete Account\n" + 
                    "5. Exit\n" + 
                    ": ");
            input = scanner.nextInt(); //get input
            System.out.println();
            
            switch(input)
            {
                case 1:
                    createAccount();
                    break;
                case 2:
                    toggleSleep();
                    break;
                case 3:
                    editInfo();
                    break;
                case 4:
                    deleteAccount();
                    break;
            }
        } while (input != 5);
    }


    /**
     * Creates a new account.
     */
    private static void createAccount()
    {
        AccountObserver newAccount = new AccountObserver(subject);
        subject.register(newAccount);
    }

    /**
     * Toggles the sleep mode of an account.
     */
    private static void toggleSleep()
    {
        System.out.print("Which account to toggle?\n" +
                ": ");
        int index = scanner.nextInt();
        subject.toggleSleep(index - 1);
        System.out.println();
    }

    /**
     * Edits the Youtube info.
     */
    private static void editInfo()
    {
        System.out.print("Enter the new subscriber count: ");
        int subscriberCount = scanner.nextInt();
        
        System.out.print("\nEnter the new video count: ");
        int videoCount = scanner.nextInt();
        
        System.out.print("\nEnter the new viewer count: ");
        int viewerCount = scanner.nextInt();
        
        subject.update(subscriberCount, videoCount, viewerCount);
    }

    /**
     * Deletes an account.
     */
    private static void deleteAccount()
    {
        System.out.print("Which account to delete?\n" +
                ": ");
        int index = scanner.nextInt();
        subject.deleteAccount(index - 1);
        System.out.println();
    }

}
