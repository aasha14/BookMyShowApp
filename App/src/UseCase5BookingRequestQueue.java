/**
 * Use Case 5: Booking Request Queue (First-Come-First-Served)
 *
 * Demonstrates Queue (FIFO) for fair request handling
 *
 * @author Aasha
 * @version 5.0
 */

import java.util.LinkedList;
import java.util.Queue;

public class UseCase5BookingRequestQueue {

    // ===== RESERVATION CLASS =====
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public void display() {
            System.out.println("Guest : " + guestName);
            System.out.println("Requested Room : " + roomType);
            System.out.println("---------------------");
        }
    }

    // ===== BOOKING QUEUE =====
    static class BookingQueue {

        private Queue<Reservation> queue = new LinkedList<>();

        // Add booking request
        public void addRequest(Reservation reservation) {
            queue.add(reservation);
            System.out.println("Request added for " + reservation.getGuestName());
        }

        // Display all requests (FIFO order)
        public void displayQueue() {
            System.out.println("\n--- Booking Request Queue ---");

            if (queue.isEmpty()) {
                System.out.println("No pending requests.");
                return;
            }

            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 5.0");
        System.out.println("=====================================");

        // Create booking queue
        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests (arrival order)
        bookingQueue.addRequest(new Reservation("Aasha", "Single"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();

        System.out.println("All requests stored in FIFO order.");
        System.out.println("No inventory updated at this stage.");
    }
}
