
public class Meeting {
    private String topic;
    private String startTime;
    private String endTime;

    public Meeting(String topic, String startTime, String endTime) {
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTopic() {
        return topic;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
