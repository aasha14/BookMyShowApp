/**
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only access, filtering, and separation of concerns
 *
 * @author Aasha
 * @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

public class UseCase4RoomSearch {

    // ===== ABSTRACT ROOM =====
    static abstract class Room {
        private String type;
        private int beds;
        private double price;

        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Room Type : " + type);
            System.out.println("Beds      : " + beds);
            System.out.println("Price     : ₹" + price);
        }

        public String getType() {
            return type;
        }
    }

    // ===== ROOM TYPES =====
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single", 1, 2000);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double", 2, 3500);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite", 3, 5000);
        }
    }

    // ===== INVENTORY (STATE HOLDER) =====
    static class Inventory {
        private Map<String, Integer> availability = new HashMap<>();

        public Inventory() {
            availability.put("Single", 5);
            availability.put("Double", 0); // unavailable
            availability.put("Suite", 2);
        }

        public int getAvailability(String type) {
            return availability.getOrDefault(type, 0);
        }
    }

    // ===== SEARCH SERVICE (READ-ONLY) =====
    static class SearchService {

        public void searchAvailableRooms(Inventory inventory, Room[] rooms) {

            System.out.println("\n--- Available Rooms ---");

            for (Room room : rooms) {

                int available = inventory.getAvailability(room.getType());

                // Filter only available rooms
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available : " + available);
                    System.out.println("---------------------");
                }
            }
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 4.0");
        System.out.println("=====================================");

        // Create inventory
        Inventory inventory = new Inventory();

        // Create room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Perform search (read-only)
        SearchService searchService = new SearchService();
        searchService.searchAvailableRooms(inventory, rooms);

        System.out.println("Search completed (No state modified).");
    }
}