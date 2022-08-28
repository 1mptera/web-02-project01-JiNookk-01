package models;

// TODO : ID, 비밀 번호, 이름, 프로필, 친구목록 -> 엔티티

import models.ChattingRoom.ChattingRoom;
import utils.Timer;

import java.util.Objects;

public class User {
    private String id;
    private String passWord;
    private String name;
    private Profile profile;

    public User(String id, String passWord, String name, Profile profile) {
        this.id = id;
        this.passWord = passWord;
        this.name = name;
        this.profile = profile;
    }

    public String id() {
        return id;
    }

    public String passWord() {
        return passWord;
    }

    public String name() {
        return name;
    }

    public void sendMessageToChattingRoom(String content, ChattingRoom chattingRoom) {
        Timer timer = new Timer();

        String time = timer.currentTime();

        chattingRoom.addMessage(content,time,id);
    }

    public Profile profile() {
        return profile;
    }

    @Override
    public boolean equals(Object other) {
        User otherUser = (User) other;

        return Objects.equals(this.id, otherUser.id);
    }

    @Override
    public String toString() {
        return "아이디: " + id + ", 비밀번호: " + passWord + ", 이름: " + name;
    }
}
