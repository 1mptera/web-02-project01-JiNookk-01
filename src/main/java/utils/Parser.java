package utils;

import models.ChattingRoom.ChattingRoom;
import models.Invitation;
import models.Message;
import models.Relation.ChattingRoomMessageRelation;
import models.Relation.UserChattingRoomRelation;
import models.Relation.UsersRelation;
import models.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public User parseUser(String line) {
        String[] words = line.split(",");

        long id = Long.parseLong(words[0]);
        String username = words[1];
        String password = words[2];
        String nickname = words[3];
        String phoneNumber = words[4];

        return new User(id, username, password, nickname, phoneNumber);
    }

    public String parseLine(long id, String userName, String password, String nickName, String phoneNumber) {
        return id + "," + userName + "," + password + "," + nickName + "," + phoneNumber;
    }

    public UsersRelation parseUserRelation(String line) {
        String[] words = line.split(",");

        long myId = Long.parseLong(words[0]);
        long friendId = Long.parseLong(words[1]);

        return new UsersRelation(myId, friendId);
    }

    public ChattingRoom parseChattingRoom(String line, List<User> users,
                                          List<UserChattingRoomRelation> userToChattingRoomRelations) {
        String[] words = line.split("/");

        long id = Long.parseLong(words[0]);
        String title = words[1];

        List<UserChattingRoomRelation> inviteds = userToChattingRoomRelations.stream()
                .filter(userChattingRoomRelation -> userChattingRoomRelation.chattingRoomId() == id)
                .toList();

        List<User> invitedUser = new ArrayList<>();

        for (UserChattingRoomRelation invited : inviteds) {
            for (User user : users) {
                if (user.id() == invited.userId()) {
                    invitedUser.add(user);
                }
            }
        }

        return new ChattingRoom(id, title, invitedUser);
    }

    public UserChattingRoomRelation parseUserChattingRoomRelation(String line) {
        String[] words = line.split(",");

        long userId = Long.parseLong(words[0]);
        long chattingRoomId = Long.parseLong(words[1]);

        return new UserChattingRoomRelation(userId, chattingRoomId);
    }

    public Message newMessage(String content, String time, long userId) throws FileNotFoundException {
        IDGenerator idGenerator = new IDGenerator();

        long id = idGenerator.newMessageId();

        return new Message(id, content, time, userId);
    }

    public Message parseMessage(String line) {
        String[] words = line.split(",");

        long id = Long.parseLong(words[0]);
        String content = words[1];
        String time = words[2];
        long userId = Long.parseLong(words[3]);

        return new Message(id,content,time,userId);
    }

    public ChattingRoomMessageRelation parseChattingRoomMessageRelation(String line) {
        String[] words = line.split(",");

        long chattingRoomId = Long.parseLong(words[0]);
        long messageId = Long.parseLong(words[1]);

        return new ChattingRoomMessageRelation(chattingRoomId, messageId);
    }

    public List<User> parseInvitedUsers(List<Invitation> invitations, List<User> users) {
        return users.stream()
                .filter(user -> invitations.stream()
                        .anyMatch(invitation -> invitation.userId() == user.id()))
                .toList();
    }

    public String parseChattingRoomTitle(List<User> invitedUsers) {
        List<String> chattingRoomUserNames = new ArrayList<>();

        invitedUsers.forEach(invitedUser -> chattingRoomUserNames.add(invitedUser.name()));

        return String.join(",", chattingRoomUserNames);
    }
}
