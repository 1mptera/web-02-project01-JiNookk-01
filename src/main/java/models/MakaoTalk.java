package models;

import models.ChattingRoom.ChattingRoom;
import utils.IDGenerator;
import utils.UserLoader;
import utils.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void addUser(User user) {
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

    public void loadUsers() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        users = userLoader.loadUser();
    }

    public void register(String userName, String password, String nickName) throws IOException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();     // TODO -> 나중에 수정해야 함.2

        String line = parser.parseLine(id,userName, password, nickName);

        User user = parser.parseUser(line);

        addUser(user);
    }
}
