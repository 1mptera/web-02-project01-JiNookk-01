package models;

import models.ChattingRoom.ChattingRoom;
import models.Relation.UsersRelation;
import utils.IDGenerator;
import utils.UserLoader;
import utils.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakaoTalk {
    private List<User> users = new ArrayList<>();
    private List<ChattingRoom> chattingRooms = new ArrayList<>();

    private long loginUserId;
    private List<UsersRelation> usersRelations = new ArrayList<>();

    public List<User> users() {
        return users;
    }

    public List<ChattingRoom> chattingRooms() {
        return chattingRooms;
    }

    public long loginUserId() {
        return loginUserId;
    }

    public List<UsersRelation> usersRelations() {
        return usersRelations;
    }

    public void updateLoginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addChattingRoom(ChattingRoom chattingRoom) {
        chattingRooms.add(chattingRoom);
    }

    public void addUsersRelation(UsersRelation usersRelation) {
        usersRelations.add(usersRelation);
    }

    public void loadUsers() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        users = userLoader.loadUser();
    }

    public User user(long friendId) {
        return users.stream()
                .filter(user -> user.id() == friendId)
                .toList()
                .get(0);
    }

    public void register(String userName, String password, String nickName, String phoneNumber) throws IOException {
        Parser parser = new Parser();

        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newUserId();     // TODO -> 나중에 수정해야 함.

        String line = parser.parseLine(id,userName, password, nickName, phoneNumber);

        User user = parser.parseUser(line);

        addUser(user);
    }

    public void login(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void requestFriend(long friendId) {
        UsersRelation usersRelation = new UsersRelation(loginUserId, friendId);

        addUsersRelation(usersRelation);
    }

    public void loadUserRelations() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        usersRelations = userLoader.loadUserRelations();
    }

    public List<UsersRelation> loginUserFriends() {
        return usersRelations.stream()
                .filter(usersRelation -> usersRelation.myId() == loginUserId)
                .toList();
    }
}
