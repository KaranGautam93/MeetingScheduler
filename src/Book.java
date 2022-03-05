import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Book {
    private Map roomsBooking;

    private static final String TIME_FORMAT = "uuuu-MM-dd HH:mm:ss";

    public Book(List roomsList) {
        initiateRoomsBooking(roomsList);
    }

    private void initiateRoomsBooking(List roomsList) {
        roomsBooking = new HashMap<Room, List<Meeting>>();

        Iterator<List> iterator = roomsList.iterator();

        while (iterator.hasNext()) {
            roomsBooking.put(iterator.next(), null);
        }
    }

    public String schedule(Meeting meeting) {
        Iterator<Map.Entry<Room, List<Meeting>>> iterator = roomsBooking.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Room, List<Meeting>> entry = iterator.next();
            List<Meeting> meetings = entry.getValue();

            if (meetings == null) {
                List<Meeting> meetingList = new ArrayList<Meeting>();

                meetingList.add(meeting);
                roomsBooking.put(entry.getKey(), meetingList);

                int id = entry.getKey().getId();
                return "Meeting " + meeting.getTopic() + " has been scheduled in room number : " + id;
            } else {
                Iterator<Meeting> meetingIterator = meetings.iterator();

                boolean canSchedule = true;

                LocalDateTime meetingToScheduleStartTime = LocalDateTime.parse(meeting.getStartTime(),
                        DateTimeFormatter.ofPattern(Book.TIME_FORMAT));
                LocalDateTime meetingToScheduleEndTime = LocalDateTime.parse(meeting.getEndTime(), DateTimeFormatter.ofPattern(Book.TIME_FORMAT));

                while (meetingIterator.hasNext()) {
                    Meeting alreadyScheduledMeeting = meetingIterator.next();
                    LocalDateTime startTime = LocalDateTime.parse(alreadyScheduledMeeting.getStartTime(), DateTimeFormatter.ofPattern(Book.TIME_FORMAT));
                    LocalDateTime endTime = LocalDateTime.parse(alreadyScheduledMeeting.getEndTime(), DateTimeFormatter.ofPattern(Book.TIME_FORMAT));

                    ZoneOffset zoneOffset = ZoneOffset.MIN;
                    if (meetingToScheduleStartTime.toEpochSecond(zoneOffset) >= startTime.toEpochSecond(zoneOffset) &&
                            meetingToScheduleEndTime.toEpochSecond(zoneOffset) <= endTime.toEpochSecond(zoneOffset)) {
                        canSchedule = false;
                        break;
                    }
                }

                if (canSchedule) {
                    meetings.add(meeting);
                    roomsBooking.put(entry.getKey(), meetings);
                    int id = entry.getKey().getId();
                    return "Meeting " + meeting.getTopic() + " has been scheduled in room number : " + id;
                }
            }
        }

        return "Error: no room available to schedule meeting '" + meeting.getTopic() + "' at given time";

    }
}
