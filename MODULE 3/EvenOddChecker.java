import java.util.Scanner;

public class EvenOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();  // Read user input

        // Check if the number is divisible by 2
        if (number % 2 == 0) {
            System.out.println(number + " is even.");
        } else {
            System.out.println(number + " is odd.");
        }

        scanner.close();  // Close the scanner
    }
}
