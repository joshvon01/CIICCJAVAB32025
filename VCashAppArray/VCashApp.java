package VCashAppArray;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main application class that runs the Gcash App.
 * It acts as the central hub, calling methods from other classes.
 */
public class VCashApp {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to GcashApp!");
        
        while (currentUser == null) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    currentUser = UserAuthentication.login(users, scanner);
                    break;
                case "2":
                    UserAuthentication.register(users, scanner);
                    break;
                case "3":
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Main menu after successful login
        mainMenu();
    }

    private static void mainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Cash-in");
            System.out.println("3. Cash-transfer");
            System.out.println("4. View all transactions");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    CheckBalance.checkBalance(currentUser);
                    break;
                case "2":
                    CashIn.performCashIn(currentUser, transactions, scanner);
                    break;
                case "3":
                    CashTransfer.performCashTransfer(currentUser, users, transactions, scanner);
                    break;
                case "4":
                    ViewTransaction.viewAllTransactions(currentUser, users, transactions);
                    break;
                case "5":
                    System.out.println("Logging out...");
                    currentUser = null;
                    main(null);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
