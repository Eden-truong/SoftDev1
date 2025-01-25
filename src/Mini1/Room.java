package Mini1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int id;
    private String name;
    private String description;
    private Map<String, Integer> navigation; //because of the way i format my map.txt i think this is the best way to implement this
    private boolean isVisited;

    public Room(int id, String name, String description) {
        this(id, name, description, new HashMap<>());
    }
    public Room(int id, String name, String description, Map<String, Integer> navigation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.navigation = navigation;
        this.isVisited = false;
    }

    public static Map<Integer, Room> mapReader(String mapFile) {
        Map<Integer, Room> gameMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
            String line;
            int currRoomId = -1; //placeholder
            Room currentRoom = null;

            while((line = br.readLine()) != null) {
                line = line.trim(); //clean up the lines cause somethings messing up

                if(line.matches("\\d+")){
                    //learned regex for this, this is SICK, checks for if its ONLY number
                    if(currentRoom != null) {
                        gameMap.put(currRoomId, currentRoom);

                    }
                    currRoomId = Integer.parseInt(line);
                    currentRoom = new Room(currRoomId, "", ""); //blank name so i can check for it
                    System.out.println("room id parsed: " + currRoomId);

                } else if (currentRoom != null && currentRoom.getName().isBlank()) {
                    currentRoom.setName(line);
                    System.out.println("Room name: " + line);

                } else if (currentRoom != null && currentRoom.getDescription().isBlank()) {
                    StringBuilder description = new StringBuilder();
                    description.append(line);

                    while ((line = br.readLine()) != null && !line.startsWith("----")) {
                        description.append("\n").append(line);

                    }

                    currentRoom.setDescription(description.toString()); // sets the full description
                    System.out.println("Room description set: " + description.toString());

                } else if(line.startsWith("----")) {//start of nav
                    while((line = br.readLine()) != null && !line.startsWith("----")) {//should be end of nav by the time 2nd pair of dash is reached
                        String[] split = line.split("\\s+");

                        if(split.length == 2) {
                            try {
                                String direction = split[0].trim();
                                int targetRoom = Integer.parseInt(split[1].trim());
                                currentRoom.getNavigation().put(direction, targetRoom);
                                System.out.println("navigation: " + direction + "->" + targetRoom);
                            } catch(NumberFormatException e) {
                                System.out.println("Invalid direction: " + line);
                            }
                        } else {
                            System.out.println("Invalid navigation line: " + line);
                        }
                    }
                }
            }
            if (currentRoom != null) {
                gameMap.put(currRoomId, currentRoom);
                currentRoom = null; //room reset

            }
        } catch(IOException e) {
            e.printStackTrace();

        }
        return gameMap;

    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNavigation(Map<String, Integer> navigation) {
        this.navigation = navigation;
    }

    public Map<String, Integer> getNavigation() {
        return navigation;
    }

    public boolean isVisited() {
        return isVisited;
    }
    public void setVisited(boolean visited) {this.isVisited = visited;}
}
