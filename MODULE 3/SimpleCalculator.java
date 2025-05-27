// SimpleCalculator.java
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for two numbers
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        // Prompt user for an operation
        System.out.println("Choose an operation: +  -  *  /");
        System.out.print("Enter your choice: ");
        char operator = scanner.next().charAt(0);

        double result = 0;
        boolean valid = true;

        // Perform the chosen operation
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;

            case '-':
                result = num1 - num2;
                break;

            case '*':
                result = num1 * num2;
                break;

            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    valid = false;
                }
                break;

            default:
                System.out.println("Invalid operator.");
                valid = false;
        }

        if (valid) {
            System.out.println("Result: " + result);
        }

        scanner.close();
    }
}
