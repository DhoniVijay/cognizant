public class CarDemo {

    // Car class with attributes and a method
    static class Car {
        String make;
        String model;
        int year;

        // Constructor
        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        // Method to display car details
        void displayDetails() {
            System.out.println("Car Details:");
            System.out.println("Make: " + make);
            System.out.println("Model: " + model);
            System.out.println("Year: " + year);
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        // Create Car objects
        Car car1 = new Car("Toyota", "Camry", 2020);
        Car car2 = new Car("Honda", "Civic", 2022);

        // Display car details
        car1.displayDetails();
        car2.displayDetails();
    }
}
