/**
 * Use Case 9: Error Handling & Validation
 *
 * Demonstrates validation, custom exceptions, and safe system behavior
 *
 * @author Aasha
 * @version 9.0
 */

import java.util.*;

public class UseCase9ErrorHandlingValidation {

    // ===== CUSTOM EXCEPTION =====
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // ===== INVENTORY =====
    static class Inventory {
        private Map<String, Integer> availability = new HashMap<>();

        public Inventory() {
            availability.put("Single", 2);
            availability.put("Double", 1);
            availability.put("Suite", 1);
        }

        public int getAvailability(String type) {
            return availability.getOrDefault(type, -1);
        }

        public void reduceAvailability(String type) throws InvalidBookingException {
            int current = getAvailability(type);

            if (current <= 0) {
                throw new InvalidBookingException("No rooms available for type: " + type);
            }

            availability.put(type, current - 1);
        }

        public void display() {
            System.out.println("\n--- Inventory ---");
            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    // ===== VALIDATOR =====
    static class BookingValidator {

        public void validate(String guestName, String roomType, Inventory inventory)
                throws InvalidBookingException {

            // Check empty name
            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            // Check valid room type
            if (inventory.getAvailability(roomType) == -1) {
                throw new InvalidBookingException("Invalid room type: " + roomType);
            }

            // Check availability
            if (inventory.getAvailability(roomType) == 0) {
                throw new InvalidBookingException("Room not available: " + roomType);
            }
        }
    }

    // ===== BOOKING SERVICE =====
    static class BookingService {

        private int counter = 1;

        public void book(String guestName, String roomType,
                         Inventory inventory, BookingValidator validator) {

            try {
                // Validation (Fail-Fast)
                validator.validate(guestName, roomType, inventory);

                // Allocation
                String roomId = roomType.substring(0, 1).toUpperCase() + counter++;

                inventory.reduceAvailability(roomType);

                System.out.println("\nBooking Successful!");
                System.out.println("Guest : " + guestName);
                System.out.println("Room Type : " + roomType);
                System.out.println("Room ID : " + roomId);

            } catch (InvalidBookingException e) {
                // Graceful error handling
                System.out.println("\nBooking Failed!");
                System.out.println("Reason: " + e.getMessage());
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 9.0");
        System.out.println("=====================================");

        Inventory inventory = new Inventory();
        BookingValidator validator = new BookingValidator();
        BookingService service = new BookingService();

        // VALID booking
        service.book("Aasha", "Single", inventory, validator);

        // INVALID room type
        service.book("Rahul", "Deluxe", inventory, validator);

        // EMPTY name
        service.book("", "Double", inventory, validator);

        // NO availability (try more than available)
        service.book("Priya", "Suite", inventory, validator);
        service.book("John", "Suite", inventory, validator);

        // Show final inventory
        inventory.display();

        System.out.println("\nSystem handled errors safely and continued execution.");
    }
}
