public class MethodOverloading {

    // Method 1: add two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Method 2: add two doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Method 3: add three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        // Call each overloaded method and display the result
        int sum1 = add(5, 10);
        double sum2 = add(4.5, 3.2);
        int sum3 = add(1, 2, 3);

        System.out.println("Sum of two integers: " + sum1);
        System.out.println("Sum of two doubles: " + sum2);
        System.out.println("Sum of three integers: " + sum3);
    }
}
