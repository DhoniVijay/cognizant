public class TypeCastingExample {
    public static void main(String[] args) {
        // Implicit casting: int to double
        int myInt = 42;
        double myDoubleFromInt = myInt;  // Automatically cast to double

        // Explicit casting: double to int
        double myDouble = 9.75;
        int myIntFromDouble = (int) myDouble;  // Manually cast to int (decimal truncated)

        // Display the results
        System.out.println("Original int: " + myInt);
        System.out.println("Int to double: " + myDoubleFromInt);

        System.out.println("Original double: " + myDouble);
        System.out.println("Double to int: " + myIntFromDouble);
    }
}
