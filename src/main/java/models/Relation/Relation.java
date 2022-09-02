package models.Relation;

import models.ChattingRoom.ChattingRoom;
import models.Message;
import utils.loader.ChattingRoomLoader;
import utils.loader.MessageLoader;
import utils.loader.UserLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Relation {
    private List<UsersRelation> usersRelations = new ArrayList<>();
    private List<UserChattingRoomRelation> userToChattingRoomRelations = new ArrayList<>();
    private List<ChattingRoomMessageRelation> chattingRoomMessageRelations = new ArrayList<>();

    private long loginUserId;
    private long currentChattingRoomId;

    public List<UsersRelation> usersRelations() {
        return new ArrayList<>(usersRelations);
    }

    public List<UserChattingRoomRelation> userChattingRoomRelations() {
        return new ArrayList<>(userToChattingRoomRelations);
    }

    public List<ChattingRoomMessageRelation> chattingRoomMessageRelations() {
        return new ArrayList<>(chattingRoomMessageRelations);
    }

    public long loginUserId() {
        return loginUserId;
    }

    public List<UsersRelation> loginUserFriends() {
        return usersRelations.stream()
                .filter(usersRelation -> usersRelation.myId() == loginUserId)
                .toList();
    }

    public List<ChattingRoomMessageRelation> currentChattingRoomMessageRelations() {
        return chattingRoomMessageRelations.stream()
                .filter(chattingRoomMessageRelation
                        -> chattingRoomMessageRelation.chattingRoomId() == currentChattingRoomId)
                .toList();
    }

    public List<UserChattingRoomRelation> loginUserChattingRoomRelations() {
        return userToChattingRoomRelations.stream()
                .filter(userChattingRoomRelation -> userChattingRoomRelation.userId() == loginUserId)
                .toList();
    }

    public void loadUserRelations() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        usersRelations = userLoader.loadUserRelations();
    }

    public void loadUserChattingRoomRelation() throws FileNotFoundException {
        ChattingRoomLoader chattingRoomLoader = new ChattingRoomLoader();

        userToChattingRoomRelations = chattingRoomLoader.loadUserToChattingRoomRelations();
    }

    public void loadChattingRoomMessageRelation() throws IOException {
        MessageLoader messageLoader = new MessageLoader();

        chattingRoomMessageRelations = messageLoader.loadChattingRoomMessageRelations();
    }

    public void addUsersRelation(UsersRelation usersRelation) {
        usersRelations.add(usersRelation);
    }

    public void addUserChattingRoomRelation(UserChattingRoomRelation userChattingRoomRelation) {
        userToChattingRoomRelations.add(userChattingRoomRelation);
    }

    public void addChattingRoomMessageRelation(ChattingRoomMessageRelation chattingRoomMessageRelation) {
        chattingRoomMessageRelations.add(chattingRoomMessageRelation);
    }

    public void requestFriend(long friendId) {
        UsersRelation usersRelation = new UsersRelation(loginUserId, friendId);

        addUsersRelation(usersRelation);
    }

    public void updateloginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public void updateCurrentChattingRoom(long currentChattingRoomId) {
        this.currentChattingRoomId = currentChattingRoomId;
    }

    public void newChattingRoomMessageRelation(ChattingRoom chattingRoom, Message newMessage) {
        addChattingRoomMessageRelation(new ChattingRoomMessageRelation(chattingRoom.id(), newMessage.id()));
    }

    public void newUserChattingRoomRelations(ChattingRoom newChatting) {
        newChatting.invitedUsers().forEach(invitedUser
                -> addUserChattingRoomRelation(
                new UserChattingRoomRelation(invitedUser.id(), newChatting.id())));
    }
}

