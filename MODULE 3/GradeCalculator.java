import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create Scanner object

        System.out.print("Enter your marks (out of 100): ");
        int marks = scanner.nextInt();  // Read user input

        // Determine grade based on marks
        if (marks >= 90 && marks <= 100) {
            System.out.println("Grade: A");
        } else if (marks >= 80) {
            System.out.println("Grade: B");
        } else if (marks >= 70) {
            System.out.println("Grade: C");
        } else if (marks >= 60) {
            System.out.println("Grade: D");
        } else if (marks >= 0) {
            System.out.println("Grade: F");
        } else {
            System.out.println("Invalid input. Marks should be between 0 and 100.");
        }

        scanner.close();  // Close the scanner
    }
}
