import java.util.HashMap;
import java.util.Map;

// Inventory class (centralized state manager)
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (booking/cancellation)
    public void updateAvailability(String roomType, int change) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            int updated = current + change;

            if (updated >= 0) {
                inventory.put(roomType, updated);
                System.out.println(roomType + " updated to: " + updated);
            } else {
                System.out.println("Not enough rooms available for " + roomType);
            }
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n===== ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial state
        inventory.displayInventory();

        // Simulate booking (reduce availability)
        inventory.updateAvailability("Single Room", -1);
        inventory.updateAvailability("Suite Room", -1);

        // Simulate cancellation (increase availability)
        inventory.updateAvailability("Double Room", +1);

        // Display updated state
        inventory.displayInventory();

        // Check availability
        System.out.println("\nSingle Room Available: " +
                inventory.getAvailability("Single Room"));
    }
}