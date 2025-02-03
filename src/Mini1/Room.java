package Mini1;

import java.util.HashMap;
import java.util.Map;

public class Room {
    int iD;
    String roomName;
    String roomDesc;
    Map<String, Integer> exits = new HashMap<>();

    public Room(int iD, String roomName, String roomDesc, Map<String, Integer> exits) {
        this.iD = iD;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.exits = exits;
    }

    public int getiD() {
        return iD;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }
}
