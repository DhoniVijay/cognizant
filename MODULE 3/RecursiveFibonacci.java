import java.util.Scanner;

public class RecursiveFibonacci {

    // Recursive method to calculate the nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;  // Base cases: fib(0) = 0, fib(1) = 1
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);  // Recursive call
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // For user input

        System.out.print("Enter a positive integer: ");
        int n = scanner.nextInt();

        // Validate input
        if (n < 0) {
            System.out.println("Fibonacci is not defined for negative numbers.");
        } else {
            int result = fibonacci(n);
            System.out.println("Fibonacci number at position " + n + " is: " + result);
        }

        scanner.close();  // Close the scanner
    }
}
