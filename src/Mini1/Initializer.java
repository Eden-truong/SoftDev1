package Mini1;

import java.util.Map;

public class Initializer {
    public static void main(String[] args) {
        String file = "C:\\Users\\edend\\IdeaProjects\\SoftDev1\\src\\Mini1\\map.txt";

        Map<Integer, Room> rooms = Room.mapReader(file);

        for(Room room : rooms.values()) {
            System.out.println("ID: " + room.getId());
            System.out.println("Name: " + room.getName());
            System.out.println("Description: " + room.getDescription());
            System.out.println("Nav: ");
            for(Map.Entry<String, Integer> entry : room.getNavigation().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
