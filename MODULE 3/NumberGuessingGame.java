import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);      // For user input
        Random random = new Random();                   // For generating random number

        int targetNumber = random.nextInt(100) + 1;     // Random number between 1 and 100
        int guess = 0;                                  // User's guess
        int attempts = 0;

        System.out.println("Guess a number between 1 and 100:");

        // Loop until the user guesses correctly
        while (guess != targetNumber) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();  // Read guess
            attempts++;

            if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed it in " + attempts + " attempt(s).");
            }
        }

        scanner.close();  // Close the scanner
    }
}
