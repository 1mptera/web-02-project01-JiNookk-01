package utils;

import models.ChattingRoom.ChattingRoom;
import models.Invitation;
import models.Message;
import models.Profile;
import models.Relation.UserChattingRoomRelation;
import models.Relation.UsersRelation;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseUser() throws IOException {
        Parser parser = new Parser();

        Profile defaultProfile = new Profile(1, deleted);

        User user1 = parser.parseUser("1,ojw,ojw123,오진욱,01085568965,false", defaultProfile);
        User user2 = new User(2, "ojw", "ojw123", "오진욱", "01085568965", defaultProfile);

        assertEquals(user2.userName(), user1.userName());
        assertEquals(user2.passWord(), user1.passWord());
        assertEquals(user2.name(), user1.name());
    }

    @Test
    void parseLine() {
        Parser parser = new Parser();

        String line = parser.parseLine(1, "ojw", "ojw123", "오진욱", "01085568965", false);

        assertEquals("1,ojw,ojw123,오진욱,01085568965,false", line);
    }

    @Test
    void parseUserRelation() {
        Parser parser = new Parser();

        UsersRelation userRelation1 = parser.parseUserRelation("1,2");

        UsersRelation userRelation2 = new UsersRelation(1, 2);

        assertEquals(userRelation1, userRelation2);
    }

    @Test
    void parseChattingRoom() throws IOException {
        Parser parser = new Parser();

        String line = "1/징성";
        List<User> users = List.of(
                new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted)),
                new User(2, "ojw0828", "7895123", "오진성", "01085568965", new Profile(1, deleted))
        );

        ChattingRoom chattingRoom = parser.parseChattingRoom(line, users, List.of());

        assertEquals(1, chattingRoom.id());
//        assertEquals(undeletedUsers, chattingRoom.invitedUsers());
    }

    @Test
    void parseUserChattingRoomRelation() {
        Parser parser = new Parser();

        UserChattingRoomRelation userRelation1 = parser.parseUserChattingRoomRelation("1,2");

        UserChattingRoomRelation userRelation2 = new UserChattingRoomRelation(1, 2);

        assertEquals(userRelation1, userRelation2);
    }

    @Test
    void newMessage() {
        Parser parser = new Parser();

//        assertEquals(new Message(1, "내용", "오늘", 1), parser.newMessage("내용", "오늘", 1));
//        assertEquals(new Message(2, "내용", "오늘", 1), parser.newMessage("내용", "오늘", 1));
    }

    @Test
    void parseMessage() {
        Parser parser = new Parser();

        Message message1 = parser.parseMessage("1,하이,22.08.30.09.35,1");

        Message message2 = new Message(1, "하이", "22.08.30.09.35", 1);

        assertEquals(message2.id(), message1.id());
        assertEquals(message2.content(), message1.content());
        assertEquals(message2.time(), message1.time());
        assertEquals(message2.userId(), message1.userId());
    }

    @Test
    void parseInvitedUsers() throws IOException {
        Parser parser = new Parser();

        List<Invitation> invitations = List.of(
                new Invitation(1)
        );

        User user1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted));
        User user2 = new User(2, "ojw0828", "7895123", "오진성", "01085568965", new Profile(1, deleted));
        User user3 = new User(2, "ojw0828", "7895123", "배준형", "01085568965", new Profile(1, deleted));

        List<User> users = List.of(user1, user2, user3);

        List<User> invitedUsers = parser.parseInvitedUsers(invitations, users);

        assertEquals(List.of(user1), invitedUsers);

        invitations = List.of(
                new Invitation(2),
                new Invitation(3)
        );

        assertEquals(List.of(user2, user3), parser.parseInvitedUsers(invitations, users));
    }

    @Test
    void parseTitle() throws IOException {
        Parser parser = new Parser();

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(new User(1, "ojw0828", "7895123", "오진욱", "01085568965", new Profile(1, deleted)));

        String title = parser.parseChattingRoomTitle(invitedUsers);

        assertEquals("오진욱", title);

        invitedUsers.add(new User(2, "ojw0828", "7895123", "오진성", "01085568965", new Profile(1, deleted)));

        title = parser.parseChattingRoomTitle(invitedUsers);

        assertEquals("오진욱,오진성", title);
    }

    @Test
    void parseProfile() throws IOException {
        Parser parser = new Parser();

        Profile profile = parser.parseProfile("1,./src/main/resources/images/jingseong.png");

        assertEquals(1,profile.id());
        assertEquals("./src/main/resources/images/jingseong.png",profile.picture().imagePath());
    }
}
