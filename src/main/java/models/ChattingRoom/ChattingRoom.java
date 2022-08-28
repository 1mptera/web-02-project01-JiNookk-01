package models.ChattingRoom;

import models.Message;
import models.User;

import java.util.List;

// TODO : 단톡방에 초대된 시용자들 + 현재 로그인한 유저 + 메시지 내역 + 단톡방 제목 + 미리보기 메시지 -> 엔티티

public class ChattingRoom {
    private List<User> invitedUsers;
    private User currentUser;
    private List<Message> messages;
    private String title;
    private String previewMessage;

    public ChattingRoom(
            List<User> invitedUsers,
            User currentUser,
            List<Message> messages,
            String title,
            String previewMessage
    ) {

        this.invitedUsers = invitedUsers;
        this.currentUser = currentUser;
        this.messages = messages;
        this.title = title;
        this.previewMessage = previewMessage;
    }

    public List<User> invitedUsers() {
        return invitedUsers;
    }

    public User currentUser() {
        return currentUser;
    }

    public String title() {
        return title;
    }

    public String previewMessage() {
        return previewMessage;
    }

    public List<Message> messages() {
        return messages;
    }

    public void addMessage(String content, String time, String id) {
        messages.add(new Message(content, time, id));
    }
}
