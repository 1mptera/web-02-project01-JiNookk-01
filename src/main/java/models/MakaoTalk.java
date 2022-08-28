package models;

import models.ChattingRoom.ChattingRoom;

import java.util.ArrayList;
import java.util.List;

public class MakaoTalk {
    private List<User> users = new ArrayList<>();
    private List<User> friends = new ArrayList<>();
    private User currentUser;
    private List<ChattingRoom> chattingRooms = new ArrayList<>();

    public List<User> users() {
        return users;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addUsers(User user) {
        users.add(user);
    }

    public User currentUser() {
        return currentUser;
    }

    public List<ChattingRoom> chattingRooms() {
        return chattingRooms;
    }

    public void addChattingRoom(ChattingRoom chattingRoom) {
        chattingRooms.add(chattingRoom);
    }

    public List<User> friends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void loadStatus() {

    }
}
