/**
 * Use Case 2: Basic Room Types & Static Availability
 * Single-file (independent) implementation
 *
 * @author Aasha
 * @version 2.0
 */

public class UseCase2RoomInitialization {

    // Abstract Room class
    static abstract class Room {
        private String roomType;
        private int beds;
        private double price;

        public Room(String roomType, int beds, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + beds);
            System.out.println("Price     : ₹" + price);
        }
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 2000);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 3500);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 2.0");
        System.out.println("=====================================");

        // Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n--- Room Details ---");

        single.displayDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println("---------------------");

        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println("---------------------");

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable);
        System.out.println("---------------------");

        System.out.println("Application Ended.");
    }
}