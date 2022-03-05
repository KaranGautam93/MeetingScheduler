import java.util.List;

public class Main {
    public static void main(String[] args) {
        Meeting meeting1 = new Meeting("Meeting 1", "2022-03-05 11:00:00", "2022-03-05 12:00:00");
        Meeting meeting2 = new Meeting("Meeting 2", "2022-03-05 11:00:00", "2022-03-05 12:00:00");
        Meeting meeting3 = new Meeting("Meeting 3", "2022-03-05 16:00:00", "2022-03-05 17:00:00");
        Meeting meeting4 = new Meeting("Meeting 4", "2022-03-05 16:00:00", "2022-03-05 17:00:00");
        Meeting meeting5 = new Meeting("Meeting 5", "2022-03-05 16:00:00", "2022-03-05 17:00:00");

        RoomFactory factory = new RoomFactory(2);

        List rooms = factory.create();

        Book book = new Book(rooms);

        System.out.println(book.schedule(meeting1));
        System.out.println(book.schedule(meeting2));
        System.out.println(book.schedule(meeting3));
        System.out.println(book.schedule(meeting4));
        System.out.println(book.schedule(meeting5));
    }
}
