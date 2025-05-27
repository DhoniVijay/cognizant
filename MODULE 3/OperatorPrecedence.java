public class OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;         // Multiplication before addition
        int result2 = (10 + 5) * 2;       // Parentheses change the order
        int result3 = 20 / 4 + 2 * 3;     // Division and multiplication first, then addition
        int result4 = 20 / (4 + 2) * 3;   // Parentheses first

        // Display results
        System.out.println("Result 1 (10 + 5 * 2): " + result1);    // 10 + (5*2) = 20
        System.out.println("Result 2 ((10 + 5) * 2): " + result2);  // (10+5) * 2 = 30
        System.out.println("Result 3 (20 / 4 + 2 * 3): " + result3); // (20/4) + (2*3) = 5 + 6 = 11
        System.out.println("Result 4 (20 / (4 + 2) * 3): " + result4); // 20 / 6 * 3 = 3 * 3 = 9
    }
}
