package models;

// TODO : ID, 비밀 번호, 이름, 프로필, 친구목록, 전화번호? -> 엔티티

import models.ChattingRoom.ChattingRoom;
import utils.Parser;
import utils.loader.UserLoader;
import utils.Timer;

import java.io.FileNotFoundException;

public class User {
    private final long id;
    private String userName;
    private String passWord;
    private String nickName;
    private Profile profile;
    private String phoneNumber;

    public User(long id, String userName, String passWord, String nickName, String phoneNumber) {
        UserLoader userLoader = new UserLoader();

        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.profile = new Profile();
        this.phoneNumber = phoneNumber;
    }

    public long id() {
        return id;
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

    public String phoneNumber() {
        return phoneNumber;
    }

    public Message sendMessageToSystem(String content) throws FileNotFoundException {
        Timer timer = new Timer();

        String time = timer.currentTime();

        Parser parser = new Parser();

        return parser.newMessage(content, time, id);
    }

    public Profile profile() {
        return profile;
    }

    public String toCsvRow() {
        return id + "," + userName + "," + passWord + "," + nickName + "," + phoneNumber;
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
