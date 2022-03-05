import java.util.ArrayList;
import java.util.List;

public class RoomFactory {
    private int capacity;
    private List rooms;

    public RoomFactory(int capacity) {
        this.capacity = capacity;
        rooms = new ArrayList<Room>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List create() {
        int idx = 1;

        for (idx = 1; idx <= capacity; idx++) {
            Room room = new Room(idx);
            rooms.add(room);
        }
        return rooms;
    }
}
