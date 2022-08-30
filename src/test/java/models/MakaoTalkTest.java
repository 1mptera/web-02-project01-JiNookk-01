package models;

import models.ChattingRoom.ChattingRoom;
import models.Relation.UserChattingRoomRelation;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakaoTalkTest {
    @Test
    void users() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<User> users = makaoTalk.users();

        User user1 = new User(1, "ojw0828", "1", "오진욱", "01085568965");

        makaoTalk.addUser(user1);

        assertEquals(user1, users.get(0));

        User user2 = new User(2, "ojs0828", "1", "오진성", "01052398955");

        makaoTalk.addUser(user2);

        assertEquals(user2, users.get(1));
    }

    @Test
    void currentUser() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User loginUser = new User(1, "ojw0828", "1", "오진욱", "01085568965");

        makaoTalk.updateLoginUserId(loginUser.id());

        assertEquals(1, makaoTalk.loginUserId());
    }

    @Test
    void chattingRooms() {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1, "hi", "20220828", 1));
        messages.add(new Message(1, "why", "20220828", 2));

        User currentUser = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");
        User otherUser = new User(2, "ojs0828", "9645123", "오진성", "01052398955");

        List<User> invitedUsers = new ArrayList<>();
        invitedUsers.add(currentUser);
        invitedUsers.add(otherUser);

        ChattingRoom chattingRoom1 = new ChattingRoom(
                1,
                "title", List.of());

        makaoTalk.addChattingRoom(chattingRoom1);

        assertEquals(chattingRoom1, makaoTalk.chattingRooms().get(0));
    }

    @Test
    void register() throws IOException {
        MakaoTalk makaotalk = new MakaoTalk();

        makaotalk.register("ojw", "ojw123", "오진욱", "phoneNumber");

//        assertEquals(List.of(new User(1, "ojw","ojw123","오진욱")),makaotalk.users());
    }

    @Test
    void login() {
        MakaoTalk makaoTalk = new MakaoTalk();

        User loginUser1 = new User(1, "ojw0828", "7895123", "오진욱", "01085568965");

        makaoTalk.login(loginUser1.id());

        assertEquals(1, makaoTalk.loginUserId());
        assertEquals(1, makaoTalk.relation().loginUserId());

        User loginUser2 = new User(2, "ojw0828", "7895123", "오진욱", "01085568965");

        makaoTalk.login(loginUser2.id());

        assertEquals(2, makaoTalk.loginUserId());
        assertEquals(2, makaoTalk.relation().loginUserId());
    }

    @Test
    void user() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965"));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965"));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965"));

        assertEquals(new User(1, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(1));
        assertEquals(new User(2, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(2));
        assertEquals(new User(3, "ojw", "ojw123", "오진욱", "01085568965"), makaoTalk.user(3));
    }

    @Test
    void messageOwnerName() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.addUser(new User(1, "ojw", "ojw123", "오진욱", "01085568965"));
        makaoTalk.addUser(new User(2, "ojs", "ojw123", "오진성", "01085568965"));
        makaoTalk.addUser(new User(3, "oja", "ojw123", "오진어", "01085568965"));

        String messageOwner = makaoTalk.messageOwnerName(new Message(1, "content", "20220828", 1));

        assertEquals("오진욱", messageOwner);
    }

    @Test
    void newChattingRoom() throws FileNotFoundException {
        MakaoTalk makaoTalk = new MakaoTalk();

        List<Invitation> invitations = List.of(
                new Invitation(2),
                new Invitation(3)
        );

        List<User> invitedUsers = List.of(
                new User(2, "ojs", "ojw123", "오진성", "01085568965"),
                new User(3, "oja", "ojw123", "배준형", "01085568965")
        );

        assertEquals(new ChattingRoom(4, "오진성,배준형", invitedUsers), makaoTalk.newChatting(invitations));
    }

    @Test
    void loginUserChattingRooms() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.login(1);

        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(1, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(3, 1));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(1, 2));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 2));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(2, 3));
        makaoTalk.relation().addUserChattingRoomRelation(new UserChattingRoomRelation(3, 3));

        User user1 = new User(1, "ojw", "ojw123", "오진욱", "01085568965");
        User user2 = new User(2, "ojs", "ojs123", "오진성", "01052398955");
        User user3 = new User(3, "bjh", "bjh123", "배준형", "01024593050");

        List<User> invitedUsers1 = List.of(user1, user2, user3);
        List<User> invitedUsers2 = List.of(user1, user2);

        makaoTalk.addChattingRoom(new ChattingRoom(1, "", invitedUsers1));
        makaoTalk.addChattingRoom(new ChattingRoom(2, "", invitedUsers2));

        List<ChattingRoom> loginUserChattingRooms = makaoTalk.relativeChattingRooms();

        assertEquals(List.of(
                new ChattingRoom(1, "첫 번쨰", invitedUsers1),
                new ChattingRoom(2, "두 번쨰", invitedUsers2)
        ), loginUserChattingRooms);
    }

    @Test
    void currentChattingRoomWithoutMessages() {
        MakaoTalk makaoTalk = new MakaoTalk();

        assertEquals(List.of(), makaoTalk.currentChattingRoomMessages());
    }

    @Test
    void currentChattingRoomOneMessage() {
        MakaoTalk makaoTalk = new MakaoTalk();

        makaoTalk.addMessage(new Message(2, "", "", 1));
        makaoTalk.addMessage(new Message(3, "", "", 1));
        makaoTalk.addMessage(new Message(4, "", "", 1));
        makaoTalk.addMessage(new Message(5, "", "", 1));
        makaoTalk.addMessage(new Message(6, "", "", 1));
        makaoTalk.addMessage(new Message(7, "", "", 1));

        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(2, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(3, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(3, "", List.of()),
                new Message(4, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(4, "", List.of()),
                new Message(5, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(1, "", List.of()),
                new Message(6, "", "", 1));
        makaoTalk.relation().newChattingRoomMessageRelation(new ChattingRoom(2, "", List.of()),
                new Message(7, "", "", 1));

        makaoTalk.relation().updateCurrentChattingRoom(1);

        assertEquals(List.of(
                        new Message(2, "", "", 1),
                        new Message(6, "", "", 1)
                ),
                makaoTalk.currentChattingRoomMessages()
        );
    }
}
