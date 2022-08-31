package models.ChattingRoom;

import models.Message;
import models.User;

import java.util.List;

// TODO : 단톡방에 초대된 시용자들 + 현재 로그인한 유저 + 메시지 내역 + 단톡방 제목 + 미리보기 메시지 -> 엔티티

public class ChattingRoom {
    private final long id;
    private List<User> invitedUsers;
    private String title;
    private String previewMessage = "";

    public ChattingRoom(long id, String title, List<User> invitedUsers) {
        this.id = id;
        this.invitedUsers = invitedUsers;
        this.title = title;
    }

    public long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String previewMessage() {
        return previewMessage;
    }

    public List<User> invitedUsers() {
        return invitedUsers;
    }

    public String toCsvRow() {
        return id + "/" + title;
    }

    @Override
    public boolean equals(Object other) {
        ChattingRoom otherChattingRoom = (ChattingRoom) other;

        return this.id == otherChattingRoom.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", 제목: " + title + ", 채팅방 유저: " + invitedUsers;
    }
}
