package models;

//TODO : 메시지 내용, 메시지 작성 시간, 메시지 작성자 ID저장            -> 밸류

import java.util.Objects;

public class Message {
    private String content;
    private String time;
    private String userId;

    public Message(String content, String time, String userId) {
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public String content() {
        return content;
    }

    public String time() {
        return time;
    }

    public String userId() {
        return userId;
    }

    @Override
    public boolean equals(Object other) {
        Message otherMessage = (Message) other;

        return Objects.equals(this.content, otherMessage.content) &&
                Objects.equals(this.time, otherMessage.time) &&
                Objects.equals(this.userId, otherMessage.userId);
    }

    @Override
    public String toString() {
        return "내용: " + content + ", 시간: " + time + ", 유저 ID: " + userId;
    }
}
