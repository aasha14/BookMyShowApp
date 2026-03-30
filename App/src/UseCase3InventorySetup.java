/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates HashMap-based inventory (single source of truth)
 *
 * @author Aasha
 * @version 3.0
 */

import java.util.HashMap;
import java.util.Map;

public class UseCase3InventorySetup {

    // ===== INVENTORY CLASS =====
    static class RoomInventory {

        private Map<String, Integer> availability;

        // Constructor → initialize inventory
        public RoomInventory() {
            availability = new HashMap<>();

            availability.put("Single", 5);
            availability.put("Double", 3);
            availability.put("Suite", 2);
        }

        // Get availability
        public int getAvailability(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        // Update availability (controlled update)
        public void updateAvailability(String roomType, int newCount) {
            availability.put(roomType, newCount);
        }

        // Display inventory
        public void displayInventory() {
            System.out.println("\n--- Current Room Inventory ---");

            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println("Room Type : " + entry.getKey());
                System.out.println("Available : " + entry.getValue());
                System.out.println("---------------------");
            }
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 3.0");
        System.out.println("=====================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example update (simulate booking)
        System.out.println("\nUpdating inventory (Booking 1 Single Room)...");

        int current = inventory.getAvailability("Single");
        inventory.updateAvailability("Single", current - 1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("Inventory management completed.");
    }
}