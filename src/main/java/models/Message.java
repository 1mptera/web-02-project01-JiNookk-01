package models;

public class Message {
    private final long id;
    private String content;
    private String time;
    private final long userId;

    public Message(long id, String content, String time, long userId) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public long id() {
        return id;
    }

    public String content() {
        return content;
    }

    public String time() {
        return time;
    }

    public long userId() {
        return userId;
    }

    public String toCsvRow() {
        return id + "," + content + "," + time + "," + userId ;
    }

    public String convertTime() {
        String[] words = time.split("\\.");
        String[] times = new String[]{words[1], words[2], words[3], words[4]};
        String time = String.join(".", times);
        return time;
    }

    @Override
    public boolean equals(Object other) {
        Message otherMessage = (Message) other;

        return this.id == otherMessage.id;
    }

    @Override
    public String toString() {
        return "ID: "+ id + ", 내용: " + content + ", 시간: " + time + ", 유저 ID: " + userId;
    }
}
