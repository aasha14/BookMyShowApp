/**
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates optional services attached to reservations
 * without modifying booking/inventory logic
 *
 * @author Aasha
 * @version 7.0
 */

import java.util.*;

public class UseCase7AddOnServiceSelection {

    // ===== SERVICE CLASS =====
    static class Service {
        private String name;
        private double price;

        public Service(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public void display() {
            System.out.println("Service : " + name + " (₹" + price + ")");
        }
    }

    // ===== ADD-ON SERVICE MANAGER =====
    static class AddOnServiceManager {

        // Map: Reservation ID → List of Services
        private Map<String, List<Service>> serviceMap = new HashMap<>();

        // Add service to reservation
        public void addService(String reservationId, Service service) {
            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);

            System.out.println("Added " + service.getName() + " to Reservation " + reservationId);
        }

        // Display services for a reservation
        public void displayServices(String reservationId) {

            System.out.println("\n--- Services for Reservation " + reservationId + " ---");

            List<Service> services = serviceMap.get(reservationId);

            if (services == null || services.isEmpty()) {
                System.out.println("No add-on services selected.");
                return;
            }

            for (Service s : services) {
                s.display();
            }
        }

        // Calculate total cost
        public double calculateTotalCost(String reservationId) {
            List<Service> services = serviceMap.get(reservationId);

            double total = 0;

            if (services != null) {
                for (Service s : services) {
                    total += s.getPrice();
                }
            }

            return total;
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay App - Version 7.0");
        System.out.println("=====================================");

        // Example reservation IDs (from UC6)
        String reservation1 = "S1";
        String reservation2 = "D1";

        // Create service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Create services
        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1500);
        Service wifi = new Service("Premium WiFi", 300);

        // Add services to reservations
        manager.addService(reservation1, breakfast);
        manager.addService(reservation1, wifi);
        manager.addService(reservation2, spa);

        // Display services
        manager.displayServices(reservation1);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation1));

        manager.displayServices(reservation2);
        System.out.println("Total Add-On Cost: ₹" + manager.calculateTotalCost(reservation2));

        System.out.println("\nAdd-on services processed successfully.");
        System.out.println("Core booking and inventory remain unchanged.");
    }
}
