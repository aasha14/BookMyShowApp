/**
 * Use Case 8: Booking History & Reporting
 *
 * Demonstrates historical tracking and reporting of bookings
 *
 * @author Aasha
 * @version 8.0
 */

import java.util.*;

public class UseCase8BookingHistoryReport {

    // ===== RESERVATION =====
    static class Reservation {
        private String guestName;
        private String roomType;
        private String roomId;

        public Reservation(String guestName, String roomType, String roomId) {
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public void display() {
            System.out.println("Guest : " + guestName);
            System.out.println("Room Type : " + roomType);
            System.out.println("Room ID : " + roomId);
            System.out.println("---------------------");
        }
    }

    // ===== BOOKING HISTORY =====
    static class BookingHistory {

        private List<Reservation> history = new ArrayList<>();

        // Add confirmed booking
        public void addBooking(Reservation reservation) {
            history.add(reservation);
        }

        // Get all bookings
        public List<Reservation> getAllBookings() {
            return history;
        }

        // Display all bookings
        public void displayHistory() {
            System.out.println("\n--- Booking History ---");

            if (history.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }

            for (Reservation r : history) {
                r.display();
            }
        }
    }

    // ===== REPORT SERVICE =====
    static class ReportService {

        public void generateReport(List<Reservation> bookings) {

            System.out.println("\n--- Booking Report ---");

            Map<String, Integer> report = new HashMap<>();

            // Count bookings per room type
            for (Reservation r : bookings) {
                report.put(r.getRoomType(),
                        report.getOrDefault(r.getRoomType(), 0) + 1);
            }

            // Display report
            for (Map.Entry<String, Integer> entry : report.entrySet()) {
                System.out.println("Room Type : " + entry.getKey());
                System.out.println("Total Bookings : " + entry.getValue());
                System.out.println("---------------------");
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 8.0");
        System.out.println("=====================================");

        // Create booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addBooking(new Reservation("Aasha", "Single", "S1"));
        history.addBooking(new Reservation("Rahul", "Double", "D1"));
        history.addBooking(new Reservation("Priya", "Suite", "SU1"));
        history.addBooking(new Reservation("John", "Single", "S2"));

        // Display history
        history.displayHistory();

        // Generate report
        ReportService reportService = new ReportService();
        reportService.generateReport(history.getAllBookings());

        System.out.println("\nReporting completed (Read-only operation).");
    }
}
