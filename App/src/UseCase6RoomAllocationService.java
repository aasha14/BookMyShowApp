/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Combines Queue + Inventory + Set to prevent double booking
 *
 * @author Aasha
 * @version 6.0
 */

import java.util.*;

public class UseCase6RoomAllocationService {

    // ===== RESERVATION =====
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
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
            return availability.getOrDefault(type, 0);
        }

        public void reduceAvailability(String type) {
            availability.put(type, availability.get(type) - 1);
        }

        public void display() {
            System.out.println("\n--- Current Inventory ---");
            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    // ===== BOOKING SERVICE =====
    static class BookingService {

        private Set<String> allocatedRoomIds = new HashSet<>();
        private Map<String, Set<String>> roomAllocations = new HashMap<>();
        private int roomCounter = 1;

        public void processBookings(Queue<Reservation> queue, Inventory inventory) {

            System.out.println("\n--- Processing Bookings ---");

            while (!queue.isEmpty()) {

                Reservation r = queue.poll();

                System.out.println("\nProcessing request for " + r.guestName);

                int available = inventory.getAvailability(r.roomType);

                if (available > 0) {

                    // Generate unique room ID
                    String roomId = r.roomType.substring(0, 1).toUpperCase() + roomCounter++;

                    // Ensure uniqueness (Set prevents duplicates)
                    if (!allocatedRoomIds.contains(roomId)) {

                        allocatedRoomIds.add(roomId);

                        // Map room type → allocated IDs
                        roomAllocations.putIfAbsent(r.roomType, new HashSet<>());
                        roomAllocations.get(r.roomType).add(roomId);

                        // Update inventory
                        inventory.reduceAvailability(r.roomType);

                        System.out.println("Booking Confirmed!");
                        System.out.println("Guest : " + r.guestName);
                        System.out.println("Room Type : " + r.roomType);
                        System.out.println("Room ID : " + roomId);

                    } else {
                        System.out.println("Error: Duplicate Room ID detected!");
                    }

                } else {
                    System.out.println("Booking Failed for " + r.guestName + " (No rooms available)");
                }
            }
        }

        public void displayAllocations() {
            System.out.println("\n--- Room Allocations ---");

            for (Map.Entry<String, Set<String>> entry : roomAllocations.entrySet()) {
                System.out.println("Room Type : " + entry.getKey());
                System.out.println("Allocated IDs : " + entry.getValue());
                System.out.println("---------------------");
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 6.0");
        System.out.println("=====================================");

        // Create queue (FIFO)
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Aasha", "Single"));
        queue.add(new Reservation("Rahul", "Double"));
        queue.add(new Reservation("Priya", "Suite"));
        queue.add(new Reservation("John", "Single")); // extra request

        // Create inventory
        Inventory inventory = new Inventory();

        // Process bookings
        BookingService service = new BookingService();
        service.processBookings(queue, inventory);

        // Display results
        service.displayAllocations();
        inventory.display();

        System.out.println("\nAll bookings processed safely (No double booking).");
    }
}