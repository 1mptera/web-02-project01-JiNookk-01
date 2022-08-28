package models;

// TODO : ID, 비밀 번호, 이름, 프로필, 친구목록 -> 엔티티

import models.ChattingRoom.ChattingRoom;
import utils.UserLoader;
import utils.MessageParser;
import utils.Timer;

public class User {
    private final long id;
    private String userName;
    private String passWord;
    private String nickName;
    private Profile profile;

    public User(long id, String userName, String passWord, String nickName) {
        UserLoader userLoader = new UserLoader();

        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.profile = userLoader.loadProfile();
    }

    public String userName() {
        return userName;
    }

    public String passWord() {
        return passWord;
    }

    public String name() {
        return nickName;
    }

    public void sendMessageToChattingRoom(String content, ChattingRoom chattingRoom) {
        Timer timer = new Timer();

        String time = timer.currentTime();

        MessageParser messageParser = new MessageParser();
        Message message = messageParser.parseMessage(content, time, userName);

        chattingRoom.receiveMessage(message);
    }

    public Profile profile() {
        return profile;
    }

    public String toCsvRow() {
        return id + "," + userName + "," + passWord + "," + nickName;
    }

    @Override
    public boolean equals(Object other) {
        User otherUser = (User) other;

        return this.id == otherUser.id;
    }

    @Override
    public String toString() {
        return "식별자: " + id + ", 아이디: " +userName + ", 비밀번호: " + passWord + ", 이름: " + nickName;
    }
}
