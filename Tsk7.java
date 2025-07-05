import java.util.Scanner; // Import the Scanner class to get user input

public class Tsk7 {

    // Method to perform addition
    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    // Method to perform subtraction
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Method to perform multiplication
    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Method to perform division
    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return Double.NaN; // Return Not-a-Number for division by zero
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        System.out.println("Welcome to the Arithmetic Operations Program!");

        // Get the first number from the user
        System.out.print("Enter the first number: ");
        double number1 = scanner.nextDouble();

        // Get the second number from the user
        System.out.print("Enter the second number: ");
        double number2 = scanner.nextDouble();

        // Perform operations using the methods and print results
        System.out.println("\nResults:");
        System.out.println("Addition: " + add(number1, number2));
        System.out.println("Subtraction: " + subtract(number1, number2));
        System.out.println("Multiplication: " + multiply(number1, number2));

        double divisionResult = divide(number1, number2);
        if (!Double.isNaN(divisionResult)) { // Check if it's not NaN (i.e., not division by zero error)
            System.out.println("Division: " + divisionResult);
        }

        scanner.close(); // Close the scanner to prevent resource leaks
    }
}