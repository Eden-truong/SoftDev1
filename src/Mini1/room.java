package Mini1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class room {
    private int id;
    private String name;
    private String description;
    private String possibleDirections;
    private Map<String, Integer> navigation; //because of the way i format my map.txt i think this is the best way to implement this
    private boolean isVisited;

    public room(int id, String name, String description) {
        this(id, name, description, new HashMap<>());
    }
    public room(int id, String name, String description, Map<String, Integer> navigation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.navigation = navigation;
        this.isVisited = false;
    }

    public Map<Integer, room> mapReader(String mapFile) {
        Map<Integer, room> gameMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
            String line;
            int currRoomId = -1; //placeholder
            room currentRoom = null;

            while((line = br.readLine()) != null) {
                if(line.matches("\\d+")){
                    //learned regex for this, this is SICK, checks for if its ONLY number
                    currRoomId = Integer.parseInt(line);
                } else if(currRoomId != -1 && currentRoom == null) {
                    currentRoom = new room(currRoomId, line, ""); //i can leave this blank to literally use it as a desc check
                } else if(currentRoom != null && currentRoom.getDescription().isBlank()) {
                    currentRoom = new room(currRoomId, currentRoom.getName(), line);
                } else if(line.startsWith("----")) {
                    
                }
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPossibleDirections() {
        return possibleDirections;
    }

    public Map<String, Integer> getNavigation() {
        return navigation;
    }

    public boolean isVisited() {
        return isVisited;
    }
    public void setVisited(boolean visited) {this.isVisited = visited;}
}
