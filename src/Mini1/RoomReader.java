package Mini1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RoomReader {
    public Map<Integer, Room> readRooms(File file) {
        Map<Integer, Room> rooms = new HashMap<Integer, Room>();
        int roomID = 0;
        String roomName = "";
        String roomDescription = "";
        Map<String, Integer> exits = new HashMap<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                while (!line.equals("-----")) {
                    String[] split = line.split("\\r?\\n");
                    roomID = Integer.parseInt(split[0]);
                    roomName = split[1];
                    roomDescription = split[2];
                }
                while (!line.equals("----")) {
                    String[] split = line.split("\\r?\\n");
                    while (!line.equals("-----")) {
                        for (int i = 0; i <= split.length; i++) {
                            String[] navSplit = split[i].split("\\s+");
                            exits.put(navSplit[0], Integer.parseInt(navSplit[1]));
                        }
                    }
                }
                rooms.put(roomID, new Room(roomID, roomName, roomDescription, exits));
            }

            // Add the last room if any
            if (!roomName.isEmpty() && !roomDescription.isEmpty()) {
                rooms.put(roomID, new Room(roomID, roomName, roomDescription, exits));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
}
