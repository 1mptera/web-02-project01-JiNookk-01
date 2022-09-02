package models;

// TODO : ID, 비밀 번호, 이름, 프로필, 친구목록, 전화번호? -> 엔티티

import utils.Parser;
import utils.Timer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class User {
    private final long id;
    private String userName;
    private String passWord;
    private String nickName;
    private String phoneNumber;
    private Profile profile;
    private boolean deleted = false;

    public User(long id, String userName, String passWord, String nickName, String phoneNumber, Profile profile) throws IOException {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
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

    public Profile profile() {
        return profile;
    }

    public boolean deleted() {
        return deleted;
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updatePassWord(String password) {
        this.passWord = password;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void loadProfile(Profile profile) {
        this.profile = profile;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void deleteID() {
        deleted = true;

        profile.delete();
    }

    public Message sendMessageToSystem(String content) throws FileNotFoundException {
        Timer timer = new Timer();

        String time = timer.currentTime();

        Parser parser = new Parser();

        return parser.newMessage(content, time, id);
    }

    public String toCsvRow() {
        return id + "," + userName + "," + passWord + "," + nickName + "," + phoneNumber + "," +deleted;
    }

    @Override
    public int hashCode() {
        return 0;
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
