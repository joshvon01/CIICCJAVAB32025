package VCashAppArray;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user login and registration logic.
 */
public class UserAuthentication {

    public static User login(ArrayList<User> users, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name) && user.getPin().equals(pin)) {
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                return user;
            }
        }
        System.out.println("Login failed. Invalid name or PIN.");
        return null;
    }

    public static void register(ArrayList<User> users, Scanner scanner) {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();

        if (pin.length() != 4 || !pin.matches("\\d+")) {
            System.out.println("Invalid PIN. It must be a 4-digit number.");
            return;
        }

        User newUser = new User(name, pin);
        users.add(newUser);
        System.out.println("Registration successful! Your user ID is: " + newUser.getId());
    }
}
